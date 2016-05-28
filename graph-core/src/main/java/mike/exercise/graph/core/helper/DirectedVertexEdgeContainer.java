package mike.exercise.graph.core.helper;

import mike.exercise.graph.core.abstraction.VertexEdgeContainer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

/**
 * This is a container holding all incoming and outgoing directed edges for a vertex, which are
 * stored in the designated collections
 *
 * @param <E>
 */
public class DirectedVertexEdgeContainer<E> implements VertexEdgeContainer {
    private static final long serialVersionUID = -12345572980606L;
    private Collection<E> incoming;
    private Collection<E> outgoing;

    public DirectedVertexEdgeContainer() {
        incoming = new HashSet<>();
        outgoing = new HashSet<>();
    }

    /**
     * get all incoming edges for the vertex
     *
     * @return
     */
    public Collection<E> getUnmodifiableIncomingEdges() {
        return Collections.unmodifiableCollection(incoming);
    }

    /**
     * get all outgoing edges
     *
     * @return
     */
    public Collection<E> getUnmodifiableOutgoingEdges() {
        return Collections.unmodifiableCollection(outgoing);
    }

    /**
     * add one incoming edge
     *
     * @param e
     */
    public void addIncomingEdge(E e) {
        incoming.add(e);
    }

    /**
     * add one outgoing edge
     *
     * @param e
     */
    public void addOutgoingEdge(E e) {
        outgoing.add(e);
    }

    /**
     * remove the incoming edge
     *
     * @param e
     */
    public void removeIncomingEdge(E e) {
        incoming.remove(e);
    }

    /**
     * remove the outgoing edge
     *
     * @param e
     */
    public void removeOutgoingEdge(E e) {
        outgoing.remove(e);
    }

    @Override
    public Collection<E> getVertexEdges() {
        Collection<E> all = new ArrayList<>(outgoing);
        all.addAll(incoming);
        return Collections.unmodifiableCollection(all);
    }
}
