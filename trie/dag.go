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
	"container/list"
	"fmt"
	"math"
	"sync"
	"sync/atomic"
)

const invalidID = math.MaxUint64

// vertex
type vertex struct {
	inDegree uint32
	outEdge  []uint64
}

// dag
type dag struct {
	vtxs     map[uint64]*vertex
	topLevel *list.List

	lock sync.Mutex
	cv   *sync.Cond

	totalVertexs  uint32
	totalConsumed uint32
}

func newDag() *dag {
	dag := &dag{
		vtxs:          make(map[uint64]*vertex),
		topLevel:      list.New(),
		totalConsumed: 0,
	}
	dag.cv = sync.NewCond(&dag.lock)
	return dag
}

func (d *dag) addVertex(id uint64) {
	if _, ok := d.vtxs[id]; !ok {
		d.vtxs[id] = &vertex{
			inDegree: 0,
			outEdge:  make([]uint64, 0),
		}
		d.totalVertexs++
	}
}

func (d *dag) delVertex(id uint64) {
	if _, ok := d.vtxs[id]; ok {
		d.totalVertexs--
		delete(d.vtxs, id)
	}
}

func (d *dag) addEdge(from, to uint64) {
	if _, ok := d.vtxs[from]; !ok {
		d.vtxs[from] = &vertex{
			inDegree: 0,
			outEdge:  make([]uint64, 0),
		}
		d.totalVertexs++
	}
	vtx := d.vtxs[from]
	found := false
	for _, id := range vtx.outEdge {
		if id == to {
			found = true
			break
		}
	}
	if !found {
		vtx.outEdge = append(vtx.outEdge, to)
	}
}

func (d *dag) generate() {
	for id, vtx := range d.vtxs {
		for _, pid := range vtx.outEdge {
			if d.vtxs[pid] == nil {
				// FIXME: for test
				panic(fmt.Sprintf("%d: out found parent id %d", id, pid))
			}
			d.vtxs[pid].inDegree++
		}
	}

	for id, vtx := range d.vtxs {
		if vtx.inDegree == 0 {
			d.topLevel.PushBack(id)
		}
	}
}

func (d *dag) waitPop() uint64 {
	if d.hasFinished() {
		return invalidID
	}

	d.cv.L.Lock()
	defer d.cv.L.Unlock()
	for d.topLevel.Len() == 0 && !d.hasFinished() {
		d.cv.Wait()
	}

	if d.hasFinished() || d.topLevel.Len() == 0 {
		return invalidID
	}

	e := d.topLevel.Front()
	id := e.Value.(uint64)
	d.topLevel.Remove(e)
	return id
}

func (d *dag) hasFinished() bool {
	return d.totalConsumed >= d.totalVertexs
}

func (d *dag) consume(id uint64) uint64 {
	var (
		producedNum        = 0
		nextID      uint64 = invalidID
		degree      uint32 = 0
	)

	for _, k := range d.vtxs[id].outEdge {
		vtx := d.vtxs[k]
		degree = atomic.AddUint32(&vtx.inDegree, ^uint32(0))
		if degree == 0 {
			producedNum += 1
			if producedNum == 1 {
				nextID = k
			} else {
				d.cv.L.Lock()
				d.topLevel.PushBack(k)
				d.cv.L.Unlock()
			}
		}
	}

	if atomic.AddUint32(&d.totalConsumed, 1) == d.totalVertexs {
		d.cv.Broadcast()
	}
	return nextID
}

func (d *dag) clear() {
	d.vtxs = make(map[uint64]*vertex)
	d.topLevel = list.New()
	d.totalConsumed = 0
	d.totalVertexs = 0
}

func (d *dag) reset() {
	for _, vtx := range d.vtxs {
		vtx.inDegree = 0
	}
	d.topLevel = list.New()
	d.totalConsumed = 0
}
