package mike.exercise.graph.core.abstraction;

import java.util.Collection;

/**
 * direct graph type
 */
public interface DirectedGraph<V, E> extends Graph<V, E> {
    /**
     * Get the number of incoming directed edges for a vertex
     *
     * @param vertex
     * @return
     */
    public int getIncomingEdgeNumber(V vertex);

    /**
     * get edges directed to a vertex.
     */
    public Collection<E> getIncomingEdges(V vertex);

    /**
     * Get the number of outgoing edges from a vertex (directed to other vertex)
     *
     * @param vertex
     * @return
     */
    public int getOutgoingEdgeNumber(V vertex);

    /**
     * Returns the edges outgoing from a vertex.
     *
     * @param vertex
     * @return
     */
    public Collection<E> getOutgoingEdges(V vertex);
}
