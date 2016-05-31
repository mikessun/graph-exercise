package mike.exercise.graph.core.impl;

import com.google.common.base.Supplier;
import mike.exercise.graph.core.interfaces.WeightedGraph;


/**
 * weighted directed graph type
 */
public class WeightedDirectedGraphImpl<V, E>
    extends DirectedGraphImpl<V, E>
    implements WeightedGraph<V, E>
{
    private static final long serialVersionUID = -9088088949100655922L;

    public WeightedDirectedGraphImpl() {
        super();
    }

    static Supplier<WeightedEdge> edgeFactory = new Supplier<WeightedEdge>() {
        @Override
        public WeightedEdge get() {
            return new WeightedEdge();
        }
    };

    @Override
    protected Edge getEdgeFromFactory( ) {
        return edgeFactory.get();
    }

    @Override
    public void setEdgeWeight(E e, double weight) {

    }
}
