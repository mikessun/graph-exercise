package mike.exercise.graph.core.impl;

import mike.exercise.graph.ResultWrapper;
import mike.exercise.graph.core.abstraction.UndirectedGraph;
import mike.exercise.graph.core.abstraction.WeightedGraph;
import mike.exercise.graph.core.exception.NotWeightEdgeException;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.*;

import static mike.exercise.graph.TestUtil.*;

/**
 * Junit tests for undirected graph
 */
public class UndirectedGraphTest {

    private UndirectedGraph graph;

    @Before
    public void setUp() {
        graph = undirectedGraphFactory.get();
    }

    @Test
    public void testAddVertex() {
        String v = vertexFactory.get();
        graph.addVertex(v);

        assertTrue(graph.containsVertex(v));
    }

    @Test
    public void testAddEdge() {
        ResultWrapper resultWrapper = buildUndirectedTestVertexGraph(graph);
        assertTrue(graph.containsEdge(resultWrapper.getE()));
    }

    @Test
    public void testGetSourceVertexForEdge() {
        ResultWrapper resultWrapper = buildUndirectedTestVertexGraph(graph);
        assertEquals(resultWrapper.getV()[0], graph.getSourceVertexForEdge(resultWrapper.getE()));
    }

    @Test
    public void testGetTargetVertexForEdge() {
        ResultWrapper resultWrapper = buildUndirectedTestVertexGraph(graph);
        assertEquals(resultWrapper.getV()[1], graph.getTargetVertexForEdge(resultWrapper.getE()));
    }

    @Test
    public void testRemoveVertex() {
        ResultWrapper resultWrapper = buildUndirectedTestVertexGraph(graph);
        assertTrue(graph.containsVertex(resultWrapper.getV()[0]));
        assertTrue(graph.removeVertex(resultWrapper.getV()[0]));
        assertTrue(!graph.containsVertex(resultWrapper.getV()[0]));
    }

    @Test
    public void testIsVerticesConnected() {
        ResultWrapper resultWrapper = buildUndirectedTestVertexGraph(graph);

        assertTrue(graph.isVerticesConnected(resultWrapper.getV()[0], resultWrapper.getV()[1]));
    }

    @Test
    public void testRemoveEdges() {
        ResultWrapper resultWrapper = buildUndirectedTestVertexGraph(graph);
        assertTrue(graph.isVerticesConnected(resultWrapper.getV()[0], resultWrapper.getV()[1]));
        assertTrue(graph.removeEdge(resultWrapper.getE()));
        assertTrue(!graph.isVerticesConnected(resultWrapper.getV()[0], resultWrapper.getV()[1]));
    }

    @Test
    public void testRemoveVertices() {
        ResultWrapper resultWrapper = buildUndirectedTestVertexGraph(graph);
        assertTrue(graph.removeVertex(resultWrapper.getV()[0]));
        assertTrue(graph.containsVertex(resultWrapper.getV()[1]));
        assertTrue(!graph.containsVertex(resultWrapper.getV()[0]));
    }

    @Test
    public void testGetAllEdges() {
        ResultWrapper resultWrapper = buildUndirectedTestVertexGraph(graph);

        assertTrue(graph.getAllEdges().contains(resultWrapper.getE()));
    }

    @Test
    public void testRemoveEdgeForVertices() {
        ResultWrapper resultWrapper = buildUndirectedTestVertexGraph(graph);

        assertTrue(graph.containsEdge(resultWrapper.getE()));
        assertTrue(graph.removeEdgeForVertices(resultWrapper.getV()[0], resultWrapper.getV()[1]));
        assertTrue(!graph.containsEdge(resultWrapper.getE()));
    }

    @Test
    public void testContainsEdge() {
        ResultWrapper resultWrapper = buildUndirectedTestVertexGraph(graph);

        assertTrue(graph.containsEdge(resultWrapper.getE()));
    }

    @Test
    public void testRemoveEdge() {
        ResultWrapper resultWrapper = buildUndirectedTestVertexGraph(graph);

        assertTrue(graph.containsEdge(resultWrapper.getE()));
        assertTrue(graph.removeEdge(resultWrapper.getE()));
        assertTrue(!graph.containsEdge(resultWrapper.getE()));
    }

    @Test
    public void testGetConnectedVertices() {
        ResultWrapper resultWrapper = buildUndirectedTestVertexGraph(graph);

        Collection connectedVertices = graph.getConnectedVertices(resultWrapper.getV()[0]);
        assertNotNull(connectedVertices);
        assertTrue("Connected vertices should not be " + connectedVertices.size(), connectedVertices.size() == 2);
    }

    @Test
    public void testGetEdges() {
        ResultWrapper resultWrapper = buildUndirectedTestVertexGraph(graph);

        Collection allEdges = graph.getAllEdges();
        assertNotNull(allEdges);
        assertTrue(allEdges.size() == 3);
    }

    @Test
    public void testGetVertices() {
        ResultWrapper resultWrapper = buildUndirectedTestVertexGraph(graph);

        Collection vertices = graph.getVertices(resultWrapper.getE());
        assertNotNull(vertices);
        assertTrue(vertices.contains(resultWrapper.getV()[0]));
        assertTrue(vertices.contains(resultWrapper.getV()[1]));
    }

    @Test
    public void testGetAllVertices() {
        ResultWrapper resultWrapper = buildUndirectedTestVertexGraph(graph);

        Collection vertices = graph.getAllVertices();
        assertNotNull(vertices);
        assertTrue(vertices.size() == 3);
    }

    @Test
    public void testContainsVertex() {
        ResultWrapper resultWrapper = buildUndirectedTestVertexGraph(graph);
        assertTrue(graph.containsVertex(resultWrapper.getV()[0]));
    }

    @Test
    public void testGetEdgeWeight() {
        ResultWrapper resultWrapper = buildUndirectedTestVertexGraph(graph);
        assertTrue(graph.containsVertex(resultWrapper.getV()[0]));
        assertTrue(graph.getEdgeWeight(resultWrapper.getE()) == WeightedGraph.DEFAULT_EDGE_WEIGHT);
    }

}