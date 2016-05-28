package mike.exercise.graph.core.impl;

import com.google.common.base.Supplier;
import lombok.extern.slf4j.Slf4j;
import mike.exercise.graph.core.abstraction.DirectedGraph;
import mike.exercise.graph.core.helper.DirectedVertexEdgeContainer;
import mike.exercise.graph.core.util.TypeUtil;

import java.util.Collection;

/**
 * concrete directed graph type
 */
@Slf4j
public class DirectedGraphImpl<V, E> extends AbstractGraph<V, E> implements DirectedGraph<V, E> {
    private static final long serialVersionUID = -345234472879671L;

    static Supplier<DirectedVertexEdgeContainer> vertexEdgeContainerFactory = new Supplier<DirectedVertexEdgeContainer>() {
        @Override
        public DirectedVertexEdgeContainer get() {
            return new DirectedVertexEdgeContainer<>();
        }
    };

    public DirectedGraphImpl() {
        super();
    }

    @Override
    protected Collection<E> doGetEdgesForVertex(V v) {
        return getDirectedVertexEdgeContainer(v).getVertexEdges();
    }

    @Override
    public void addEdgeToTouchingVertices(E e) {
        getDirectedVertexEdgeContainer(getSourceVertexForEdge(e)).addOutgoingEdge(e);
        getDirectedVertexEdgeContainer(getTargetVertexForEdge(e)).addOutgoingEdge(e);
    }

    @Override
    public void removeEdgeFromTouchingVertices(E e) {
        getDirectedVertexEdgeContainer(getSourceVertexForEdge(e)).removeOutgoingEdge(e);
        getDirectedVertexEdgeContainer(getTargetVertexForEdge(e)).removeIncomingEdge(e);
    }

    @Override
    public int getIncomingEdgeNumber(V vertex) {
        return getIncomingEdges(vertex).size();
    }

    @Override
    public Collection<E> getIncomingEdges(V vertex) {
        return getDirectedVertexEdgeContainer(vertex).getUnmodifiableIncomingEdges();
    }

    @Override
    public int getOutgoingEdgeNumber(V vertex) {
        return getOutgoingEdges(vertex).size();
    }

    @Override
    public Collection<E> getOutgoingEdges(V vertex) {
        return getDirectedVertexEdgeContainer(vertex).getUnmodifiableOutgoingEdges();
    }

    private DirectedVertexEdgeContainer getDirectedVertexEdgeContainer(V v) {
        return TypeUtil.uncheckedCast(getEdgeContainer(v, DirectedVertexEdgeContainer.class), null);
    }
}
