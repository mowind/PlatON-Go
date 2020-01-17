// Copyright 2018-2020 The PlatON Network Authors
// This file is part of the PlatON-Go library.
//
// The PlatON-Go library is free software: you can redistribute it and/or modify
// it under the terms of the GNU Lesser General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// The PlatON-Go library is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
// GNU Lesser General Public License for more details.
//
// You should have received a copy of the GNU Lesser General Public License
// along with the PlatON-Go library. If not, see <http://www.gnu.org/licenses/>.

package trie

import (
	"sync"
	"sync/atomic"

	"runtime"

	"github.com/PlatONnetwork/PlatON-Go/common"
	"github.com/cespare/xxhash"
	"github.com/panjf2000/ants/v2"
)

var fullNodeSuffix = []byte("fullnode")

type Dagger interface {
	add(pprefix, prefix []byte, n node)
	delete(id uint64)
	deleteByKey(key []byte)
	deleteByNode(prefix []byte, n node)
	reset()
	hash(*Database, LeafCallback, bool) (node, node, error)
	size() int
}

type defaultDag struct {
	Dagger
}

func newDefaultDag() *defaultDag {
	return &defaultDag{}
}

func (dd *defaultDag) add(pprefix, prefix []byte, n node) {}

func (dd *defaultDag) delete(uint64) {}

func (dd *defaultDag) deleteByKey([]byte) {}

func (dd *defaultDag) deleteByNode([]byte, node) {}

func (dd *defaultDag) hash(*Database, LeafCallback, bool) (node, node, error) {
	return nil, nil, nil
}

func (dd *defaultDag) size() int {
	return 0
}

func (dd *defaultDag) reset() {}

// dagNode
type dagNode struct {
	collapsed node
	cached    node

	pid uint64
	idx int
}

// trieDag
type trieDag struct {
	nodes map[uint64]*dagNode
	dag   *dag

	lock sync.Mutex

	cachegen   uint16
	cachelimit uint16
}

func newTrieDag() *trieDag {
	return &trieDag{
		nodes:      make(map[uint64]*dagNode),
		dag:        newDag(),
		cachegen:   0,
		cachelimit: 0,
	}
}

func (td *trieDag) add(pprefix, prefix []byte, n node) {
	td.lock.Lock()
	defer td.lock.Unlock()
	td.internalAdd(pprefix, prefix, n, true)
}

func (td *trieDag) internalAdd(pprefix, prefix []byte, n node, recursive bool) {
	var pid uint64
	if len(pprefix) > 0 {
		pid = xxhash.Sum64(pprefix)
	}

	switch n := n.(type) {
	case *shortNode:
		collapsed, cached := n.copy(), n.copy()
		collapsed.Key = hexToCompact(n.Key)
		cached.Key = common.CopyBytes(n.Key)

		id := xxhash.Sum64(append(prefix, n.Key...))
		td.nodes[id] = &dagNode{
			collapsed: collapsed,
			cached:    cached,
			pid:       pid,
		}
		if len(prefix) > 0 {
			td.nodes[id].idx = int(prefix[len(prefix)-1])
		}
		td.dag.addVertex(id)

		if pid > 0 {
			td.dag.addEdge(id, pid)
		}
	case *fullNode:
		collapsed, cached := n.copy(), n.copy()
		cached.Children[16] = n.Children[16]

		dn := &dagNode{
			collapsed: collapsed,
			cached:    cached,
			pid:       pid,
		}
		if len(prefix) > 0 {
			dn.idx = int(prefix[len(prefix)-1])
		}

		id := xxhash.Sum64(append(prefix, fullNodeSuffix...))
		td.nodes[id] = dn
		td.dag.addVertex(id)
		if pid > 0 {
			td.dag.addEdge(id, pid)
		}

		if recursive {
			for i := 0; i < 16; i++ {
				if n.Children[i] != nil {
					cn := n.Children[i]
					td.internalAdd(append(prefix, fullNodeSuffix...), append(prefix, byte(i)), cn, false)
				}
			}
		}
	}
}

func (td *trieDag) delete(id uint64) {
	td.lock.Lock()
	defer td.lock.Unlock()
	td.dag.delVertex(id)
	delete(td.nodes, id)
}

func (td *trieDag) deleteByNode(prefix []byte, n node) {
	var id uint64
	switch n := n.(type) {
	case *shortNode:
		id = xxhash.Sum64(append(prefix, n.Key...))
	case *fullNode:
		id = xxhash.Sum64(append(prefix, fullNodeSuffix...))
	}
	td.delete(id)
}

func (td *trieDag) deleteByKey(key []byte) {
	td.delete(xxhash.Sum64(key))
}

func (td *trieDag) reset() {
	td.lock.Lock()
	defer td.lock.Unlock()

	td.dag.clear()
	td.nodes = make(map[uint64]*dagNode)
}

func (td *trieDag) hash(db *Database, onleaf LeafCallback, force bool) (node, node, error) {
	td.lock.Lock()
	defer td.lock.Unlock()

	td.dag.generate()

	var (
		wg        sync.WaitGroup
		errDone   common.AtomicBool
		ae        atomic.Value // error
		finalHash node         = hashNode{}
		root      node
		numCPU    = runtime.NumCPU()
	)

	cachedHash := func(n, c node) (node, node, bool) {
		if hash, dirty := c.cache(); len(hash) != 0 {
			if db == nil {
				return hash, c, true
			}

			if c.canUnload(td.cachegen, td.cachelimit) {
				cacheUnloadCounter.Inc(1)
				return hash, hash, true
			}

			if !dirty {
				return hash, c, true
			}
		}
		return n, c, false
	}

	compute := func() {
		hasher := newHasher(td.cachegen, td.cachelimit, onleaf)

		id := td.dag.waitPop()
		if id == invalidID {
			returnHasherToPool(hasher)
			wg.Done()
			return
		}

		var (
			hashed   node
			cached   node
			err      error
			hasCache bool
		)

		for id != invalidID {
			n := td.nodes[id]

			tmpForce := false
			if n.pid == 0 {
				tmpForce = force
			}

			hashed, cached, hasCache = cachedHash(n.collapsed, n.cached)
			if !hasCache {
				hashed, err = hasher.store(n.collapsed, db, tmpForce)
				if err != nil {
					ae.Store(err)
					errDone.Set(true)
					break
				}
				cached = n.cached
			}

			if n.pid > 0 {
				p := td.nodes[n.pid]
				switch ptype := p.collapsed.(type) {
				case *shortNode:
					ptype.Val = hashed
				case *fullNode:
					ptype.Children[n.idx] = hashed
				}

				if _, ok := cached.(hashNode); ok {
					switch nc := p.cached.(type) {
					case *shortNode:
						nc.Val = cached
					case *fullNode:
						nc.Children[n.idx] = cached
					}
				}
			}

			cachedHash, _ := hashed.(hashNode)
			switch cn := n.cached.(type) {
			case *shortNode:
				*cn.flags.hash = cachedHash
				if db != nil {
					*cn.flags.dirty = false
				}
			case *fullNode:
				*cn.flags.hash = cachedHash
				if db != nil {
					*cn.flags.dirty = false
				}
			}

			id = td.dag.consume(id)
			if n.pid == 0 {
				finalHash = hashed
				root = n.cached
				break
			}

			if errDone.IsSet() {
				break
			}

			if id == invalidID && !td.dag.hasFinished() {
				id = td.dag.waitPop()
			}
		}
		returnHasherToPool(hasher)
		wg.Done()
	}

	wg.Add(numCPU)
	for i := 0; i < numCPU; i++ {
		_ = ants.Submit(compute)
	}

	wg.Wait()
	td.dag.reset()

	if ae.Load() != nil && ae.Load().(error) != nil {
		return hashNode{}, nil, ae.Load().(error)
	}
	return finalHash, root, nil
}

func (td *trieDag) size() int {
	return len(td.nodes)
}
