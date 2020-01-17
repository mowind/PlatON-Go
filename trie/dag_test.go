package trie

import (
	"sync"
	"testing"
	"time"

	"github.com/stretchr/testify/assert"
)

func TestDag(t *testing.T) {
	dag := newDag()

	dag.addVertex(1)
	dag.addVertex(2)
	dag.addVertex(3)
	dag.addVertex(4)
	dag.addVertex(5)
	assert.True(t, len(dag.vtxs) == 5)
	assert.True(t, len(dag.vtxs) == int(dag.totalVertexs))

	dag.delVertex(5)
	assert.True(t, len(dag.vtxs) == 4)
	assert.True(t, len(dag.vtxs) == int(dag.totalVertexs))

	dag.addVertex(5)
	dag.addVertex(5)
	assert.True(t, len(dag.vtxs) == 5)

	dag.addEdge(1, 2)
	dag.addEdge(2, 3)
	dag.addEdge(3, 4)
	dag.addEdge(4, 5)

	dag.addEdge(4, 5)
	assert.True(t, len(dag.vtxs[4].outEdge) == 1)

	dag.generate()
	assert.True(t, dag.topLevel.Len() == 1)
	assert.True(t, dag.waitPop() == 1)
	nextID := dag.consume(1)
	assert.True(t, dag.totalConsumed == 1)
	assert.True(t, nextID == 2)
	nextID = dag.consume(nextID)
	nextID = dag.consume(nextID)
	nextID = dag.consume(nextID)
	nextID = dag.consume(nextID)
	assert.True(t, nextID == invalidID)
	assert.True(t, dag.hasFinished())
	assert.True(t, dag.waitPop() == invalidID)

	dag.addEdge(6, 5)
	assert.True(t, len(dag.vtxs) == 6)

	dag.reset()
	assert.True(t, dag.topLevel.Len() == 0)
	assert.True(t, dag.totalConsumed == 0)
	assert.True(t, len(dag.vtxs) == int(dag.totalVertexs))

	dag.clear()
	assert.True(t, len(dag.vtxs) == 0)
	assert.True(t, dag.totalVertexs == 0)
}

func TestDagConcurrency(t *testing.T) {
	dag := newDag()

	for i := 0; i <= 11; i++ {
		dag.addVertex(uint64(i))
	}

	dag.addEdge(0, 5)
	dag.addEdge(0, 10)
	dag.addEdge(0, 11)
	dag.addEdge(1, 5)
	dag.addEdge(2, 5)
	dag.addEdge(3, 5)
	dag.addEdge(4, 5)
	dag.addEdge(5, 6)
	dag.addEdge(7, 6)
	dag.addEdge(6, 8)
	dag.addEdge(8, 9)
	dag.generate()

	var wg sync.WaitGroup
	wg.Add(8)
	for i := 0; i < 8; i++ {
		go func() {
			v := dag.waitPop()
			for v != invalidID {
				// Ensure testing all branches
				time.Sleep(10 * time.Millisecond)
				v = dag.consume(v)
				if v == invalidID && !dag.hasFinished() {
					v = dag.waitPop()
				}
			}
			wg.Done()
		}()
	}
	wg.Wait()

	assert.True(t, dag.hasFinished())
}
