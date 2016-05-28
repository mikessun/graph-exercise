package mike.exercise.graph.core.abstraction;

/**
 * Graph type with weighted edges
 */
public interface WeightedGraph<V, E>
        extends Graph<V, E> {

    public double DEFAULT_EDGE_WEIGHT = 1;

    /**
     * Assigns a weight to an edge.
     *
     * @param e
     * @param weight
     */
    public void setEdgeWeight(E e, double weight);
}
