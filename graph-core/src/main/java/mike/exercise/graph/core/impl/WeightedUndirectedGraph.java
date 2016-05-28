package mike.exercise.graph.core.impl;


import com.google.common.base.Supplier;
import mike.exercise.graph.core.abstraction.WeightedGraph;
import mike.exercise.graph.core.exception.NotWeightEdgeException;
import mike.exercise.graph.core.util.TypeUtil;

/**
 * undirected weighted graph type, all of edges must have a weight;
 * default edge weight to 0;
 */
public class WeightedUndirectedGraph<V, E>
        extends UndirectedGraphImpl<V, E>
        implements WeightedGraph<V, E> {
    private static final long serialVersionUID = -6890088949100655922L;

    public WeightedUndirectedGraph() {
        super();
    }

    @Override
    public void setEdgeWeight(E e, double weight) {
        if (edgeMap.get(e).getClass().isAssignableFrom(WeightedEdge.class)) {
            throw new NotWeightEdgeException();
        }

        WeightedEdge we = TypeUtil.uncheckedCast(edgeMap.get(e), null);
        we.setWeight(weight);
    }

    static Supplier<WeightedEdge> edgeFactory = new Supplier<WeightedEdge>() {
        @Override
        public WeightedEdge get() {
            return new WeightedEdge();
        }
    };

    @Override
    protected Edge getEdgeFromFactory() {
        return edgeFactory.get();
    }
}
