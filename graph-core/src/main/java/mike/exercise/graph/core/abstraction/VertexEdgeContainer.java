package mike.exercise.graph.core.abstraction;

import java.io.Serializable;
import java.util.Collection;

/**
 * Vertex edge container type
 */
public interface VertexEdgeContainer<E> extends Serializable {
    /**
     * get all edges for the vertex
     *
     * @return
     */
    public Collection<E> getVertexEdges();
}
