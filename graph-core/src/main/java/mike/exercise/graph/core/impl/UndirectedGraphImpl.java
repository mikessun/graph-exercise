package mike.exercise.graph.core.impl;

import com.google.common.base.Supplier;
import mike.exercise.graph.core.abstraction.UndirectedGraph;
import mike.exercise.graph.core.exception.NotWeightEdgeException;
import mike.exercise.graph.core.helper.UndirectedVertexEdgeContainer;
import mike.exercise.graph.core.util.TypeUtil;

import java.util.Collection;

/**
 * undirected graph type
 */
public class UndirectedGraphImpl<V, E> extends AbstractGraph<V, E> implements UndirectedGraph<V, E> {
    private static final long serialVersionUID = -69874589454112304L;
    static Supplier<UndirectedVertexEdgeContainer> vertexEdgeContainerFactory = new Supplier<UndirectedVertexEdgeContainer>() {
        @Override
        public UndirectedVertexEdgeContainer get() {
            return new UndirectedVertexEdgeContainer<>();
        }
    };

    public UndirectedGraphImpl() {
        super();
    }

    @Override
    protected Collection<E> doGetEdgesForVertex(V v) {
        return getUndirectedVertexEdgeContainer(v).getVertexEdges();
    }

    @Override
    protected void addEdgeToTouchingVertices(E e) {
        getUndirectedVertexEdgeContainer(getSourceVertexForEdge(e)).addEdge(e);
        getUndirectedVertexEdgeContainer(getTargetVertexForEdge(e)).addEdge(e);
    }

    @Override
    protected void removeEdgeFromTouchingVertices(E e) {
        getUndirectedVertexEdgeContainer(getSourceVertexForEdge(e)).removeEdge(e);
    }

    private UndirectedVertexEdgeContainer getUndirectedVertexEdgeContainer(V v) {
        return TypeUtil.uncheckedCast(getEdgeContainer(v, UndirectedVertexEdgeContainer.class), null);
    }
}

