package mike.exercise.graph.algorithm.path;

import mike.exercise.graph.TestUtil;
import mike.exercise.graph.core.interfaces.Graph;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.*;

public class DijkstraShortestPathTest {
    public static int TOTAL_VERTICE = 11;
    public static int TOTAL_EDGES = 12;
    private DijkstraShortestPath dijkstraShortestPath;
    private Graph graph;
    private String[] vertex = new String[TOTAL_VERTICE];

    @Before
    public void bofore() {
        graph = TestUtil.undirectedGraphFactory.get();
        vertex = new String[TOTAL_VERTICE];
        Integer edges[] = new Integer[TOTAL_EDGES];
        for (int i = 0; i < vertex.length; i++) {
            vertex[i] = TestUtil.vertexFactory.get();
            graph.addVertex(vertex[i]);
        }
        for (int i = 0; i < edges.length; i++) {
            edges[i] = TestUtil.edgeFactory.get();
        }

        graph.addEdge(vertex[0], vertex[1], edges[0]);
        graph.addEdge(vertex[0], vertex[2], edges[1]);
        graph.addEdge(vertex[0], vertex[4], edges[2]);
        graph.addEdge(vertex[2], vertex[6], edges[3]);
        graph.addEdge(vertex[2], vertex[7], edges[4]);
        graph.addEdge(vertex[3], vertex[7], edges[5]);
        graph.addEdge(vertex[5], vertex[8], edges[6]);
        graph.addEdge(vertex[8], vertex[9], edges[7]);
        graph.addEdge(vertex[7], vertex[9], edges[8]);
        graph.addEdge(vertex[4], vertex[9], edges[9]);
        graph.addEdge(vertex[9], vertex[10], edges[10]);
        graph.addEdge(vertex[1], vertex[10], edges[11]);

        dijkstraShortestPath = new DijkstraShortestPath(graph);

    }

    @Test
    public void testGetPath() throws Exception {
        LinkedList path = dijkstraShortestPath.getPath(vertex[0], vertex[10]);

        assertTrue("should not such path: " + path, path.size() == 3);
    }

    @Test
    public void testGetPath1() throws Exception {
        LinkedList path = dijkstraShortestPath.getPath(vertex[0], vertex[9]);

        assertTrue("should not such path: " + path, path.size() == 3);
    }
}