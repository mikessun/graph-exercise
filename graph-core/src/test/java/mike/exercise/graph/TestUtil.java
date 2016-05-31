package mike.exercise.graph;

import com.google.common.base.Supplier;
import mike.exercise.graph.core.interfaces.DirectedGraph;
import mike.exercise.graph.core.interfaces.Graph;
import mike.exercise.graph.core.interfaces.UndirectedGraph;
import mike.exercise.graph.core.impl.DirectedGraphImpl;
import mike.exercise.graph.core.impl.UndirectedGraphImpl;

import java.util.concurrent.ThreadLocalRandom;

/**
 * test utility
 */
public class TestUtil {

    private TestUtil() {
    }

    public static Supplier<Integer> edgeFactory = new Supplier<Integer>() {
        int count = 10;

        @Override
        public Integer get() {
            return count++;
        }
    };
    public static Supplier<String> vertexFactory = new Supplier<String>() {
        int count = 10;

        @Override
        public String get() {
            return "v" + count++;
        }
    };

    public static Supplier<DirectedGraph> directGraphFactory = new Supplier<DirectedGraph>() {
        @Override
        public DirectedGraph get() {
            return new DirectedGraphImpl<>();
        }
    };
    public static Supplier<UndirectedGraph> undirectedGraphFactory = new Supplier<UndirectedGraph>() {
        @Override
        public UndirectedGraph get() {
            return new UndirectedGraphImpl<>();
        }
    };

    public static Supplier<Double> doubleFactory = new Supplier<Double>() {
        @Override
        public Double get() {
            return ThreadLocalRandom.current().nextDouble(1, 20);
        }
    };

    /**
     * create vertex array and add to the graph
     *
     * @param graph
     * @param noOfVertices
     * @return
     */
    public static String[] addVertices(Graph graph, int noOfVertices) {
        String v[] = new String[noOfVertices];
        for (int i = 0; i < v.length; i++) {
            v[i] = vertexFactory.get();
            graph.addVertex(v[i]);
        }
        return v;
    }

    /**
     * build a graph with 3 vertices and return only one edge connecting two vertices
     *
     * @param graph
     * @return ResultWrapper
     */
    public static ResultWrapper buildUndirectedTestVertexGraph(Graph graph) {
        String[] v = addVertices(graph,3);
        Integer e = edgeFactory.get();
        graph.addEdge(v[0], v[1], e);
        graph.addEdge(v[0], v[2], edgeFactory.get());
        graph.addEdge(v[1], v[2], edgeFactory.get());

        return new ResultWrapper(v,e);
    }
}

