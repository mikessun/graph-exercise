package mike.exercise.graph.core.abstraction;

/**
 * edge factory to create new edges
 */
public interface EdgeFactory<V, E> {
    /**
     * create a new edge
     *
     * @return
     */
    public E createEdge();
}