package mike.exercise.graph.core.helper;

import mike.exercise.graph.core.interfaces.VertexEdgeContainer;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

/**
 * This is a container holding all incoming and outgoing undirected edges for a vertex, which are
 * stored in the designated collections
 */
public class UndirectedVertexEdgeContainer<E> implements VertexEdgeContainer<E> {
    private static final long serialVersionUID = -65342075834210L;
    Collection<E> vertexEdges = new HashSet<>();

    /**
     * add an edge
     *
     * @param e
     */
    public void addEdge(E e) {
        vertexEdges.add(e);
    }

    /**
     * get edge count
     *
     * @return
     */
    public int edgeCount() {
        return vertexEdges.size();
    }

    /**
     * remove the edge
     *
     * @param e
     */
    public void removeEdge(E e) {
        vertexEdges.remove(e);
    }

    @Override
    public Collection<E> getVertexEdges() {
        return Collections.unmodifiableCollection(vertexEdges);
    }
}
