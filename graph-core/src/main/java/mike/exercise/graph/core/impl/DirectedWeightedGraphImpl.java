package mike.exercise.graph.core.impl;


import com.google.common.base.Supplier;
import mike.exercise.graph.core.interfaces.WeightedGraph;
import mike.exercise.graph.core.exception.NotWeightEdgeException;
import mike.exercise.graph.core.util.TypeUtil;

/**
 * directed weighted graph type, all of edges must have a weight;
 * default edge weight to 0;
 */
public class DirectedWeightedGraphImpl<V, E> extends DirectedGraphImpl<V, E> implements WeightedGraph<V, E> {
    private static final long serialVersionUID = -2548960841681220919L;

    public DirectedWeightedGraphImpl() {
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