package mike.exercise.graph.core.impl;

import com.google.common.base.Supplier;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import mike.exercise.graph.core.abstraction.DirectedGraph;
import mike.exercise.graph.core.abstraction.Graph;
import mike.exercise.graph.core.abstraction.VertexEdgeContainer;
import mike.exercise.graph.core.abstraction.WeightedGraph;
import mike.exercise.graph.core.exception.*;
import mike.exercise.graph.core.util.TypeUtil;
import org.apache.commons.collections.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A skeletal implementation of graph type
 */
@Slf4j
public abstract class AbstractGraph<V, E> implements Graph<V, E> {
    private static final long serialVersionUID = -16987456872427L;

    protected Map<V, VertexEdgeContainer<E>> vertexMap;

    protected Map<E, Edge> edgeMap;
    private transient Set<E> unmodifiableEdgeSet = null;
    private transient Set<V> unmodifiableVertexSet = null;
    private transient TypeUtil<V> vertexTypeDecl = null;

    protected AbstractGraph() {
        edgeMap = new HashMap<>();
        vertexMap = new HashMap<>();
    }

    @Override
    public boolean addVertex(@NonNull V v) {
        if (containsVertex(v)) {
            String s = "vertex already exists!";
            log.error(s);
            throw new VertexAlreadyExistException(s);
        }
        vertexMap.put(v, null);
        return true;
    }

    @Override
    public boolean addEdge(V sourceVertex, V targetVertex, E e) {
        boolean result = false;
        doesVertexExist(sourceVertex);
        doesVertexExist(targetVertex);

        if (isVerticesConnected(sourceVertex, targetVertex)) {
            return false;
        }

        if (!containsEdge(e)) {
            Edge edge = createEdge(e, sourceVertex, targetVertex);

            edgeMap.put(e, edge);
            addEdgeToTouchingVertices(e);

            result = true;
        }
        log.debug("addEdge result: {}", result);
        return result;
    }

    @Override
    public V getSourceVertexForEdge(E e) {
        return TypeUtil.uncheckedCast(
                getEdge(e).getSource(),
                vertexTypeDecl);
    }

    @Override
    public V getTargetVertexForEdge(E e) {
        return TypeUtil.uncheckedCast(getEdge(e).getTarget(), vertexTypeDecl);
    }


    @Override
    public boolean removeVertex(V v) {
        if (!containsVertex(v)) {
            throw new VertexNotExistException();
        }

        Collection<E> touchingEdgesList = getEdges(v);
        removeAllEdges(touchingEdgesList);

        vertexMap.keySet().remove(v);

        return true;
    }

    @Override
    public boolean isVerticesConnected(V sourceVertex, V targetVertex) {
        return getEdges(sourceVertex, targetVertex) != null;
    }

    @Override
    public boolean removeEdges(Collection<? extends E> edges) {
        boolean modified = false;

        for (E e : edges) {
            modified |= removeEdge(e);
        }

        return modified;
    }

    @Override
    public boolean removeVertices(Collection<? extends V> vertices) {
        boolean modified = false;

        for (V v : vertices) {
            modified |= removeVertex(v);
        }

        return modified;
    }

    @Override
    public Collection<E> getAllEdges() {
        if (unmodifiableEdgeSet == null) {
            unmodifiableEdgeSet = Collections.unmodifiableSet(edgeMap.keySet());
        }

        return unmodifiableEdgeSet;
    }

    @Override
    public boolean removeEdgeForVertices(V sourceVertex, V targetVertex) {
        E e = getEdges(sourceVertex, targetVertex);

        return removeEdge(e);
    }

    @Override
    public boolean containsEdge(E e) {
        return edgeMap.containsKey(e);
    }

    @Override
    public boolean removeEdge(@NonNull E e) {
        if (!containsEdge(e)) {
            throw new EdgeNotExistException();
        }
        removeEdgeFromTouchingVertices(e);
        edgeMap.remove(e);
        return true;
    }

    @Override
    public Collection<V> getConnectedVertices(@NonNull V v) {
        VertexEdgeContainer container = vertexMap.get(v);
        if (container == null || CollectionUtils.isEmpty(container.getVertexEdges())) {
            return Collections.EMPTY_LIST;
        }

        Collection<V> connectedVertices = new HashSet<>();
        container.getVertexEdges().forEach(edge -> {
                    Collection<V> vertices = getVertices(TypeUtil.uncheckedCast(edge, null));
                    Optional<V> any = vertices.stream().filter(vertex -> !vertex.equals(v)).findAny();
                    if (any.isPresent()) {
                        connectedVertices.add(any.get());
                    }
                }
        );
        return connectedVertices;
    }

    /**
     * return the graph as a string
     *
     * @return
     */
    @Override
    public String toString() {
        return getStringRepresentation(
                getAllVertices(),
                getAllEdges(), this.getClass().isAssignableFrom(DirectedGraph.class));
    }

    /**
     * check if the vertex exists in the graph
     *
     * @param v
     * @return
     */
    protected boolean doesVertexExist(@NonNull V v) {
        if (containsVertex(v)) {
            return true;
        }
        throw new VertexNotExistException("vertex: " + v);
    }

    @Override
    public Collection<E> getEdges(V v) {
        if (!containsVertex(v)) {
            throw new VertexNotExistException();
        }
        return doGetEdgesForVertex(v);
    }

    protected abstract Collection<E> doGetEdgesForVertex(V v);

    @Override
    public Collection<V> getVertices(E e) {
        if (!containsEdge(e)) {
            log.error("the edge not exist!");
            throw new EdgeNotExistException();
        }
        Collection<V> vertices = new ArrayList<>(2);
        vertices.add(getSourceVertexForEdge(e));
        vertices.add(getTargetVertexForEdge(e));

        return Collections.unmodifiableCollection(vertices);
    }

    @Override
    public Collection<V> getAllVertices() {
        return vertexMap.keySet();
    }

    @Override
    public boolean containsVertex(V v) {
        return vertexMap.keySet().contains(v);
    }

    @Override
    public E getEdges(V v1, V v2) {
        Collection<E> vertexEdges1 = vertexMap.get(v1) != null ? vertexMap.get(v1).getVertexEdges() : Collections.EMPTY_LIST;
        Collection<E> vertexEdges2 = vertexMap.get(v2) != null ? vertexMap.get(v2).getVertexEdges() : Collections.EMPTY_LIST;

        Optional<E> optional = vertexEdges1.stream().filter(edge1 -> vertexEdges2.stream().filter(edge2 -> edge1.equals(edge2)).findAny().isPresent()).findAny();

        return optional.isPresent() ? optional.get() : null;
    }


    @Override
    public double getEdgeWeight(@NonNull E e) {
        Edge edge = edgeMap.get(e);
        if (WeightedEdge.class.isAssignableFrom(edge.getClass())) {
            WeightedEdge we = TypeUtil.uncheckedCast(edge, null);
            return we.getWeight();
        }
        return WeightedGraph.DEFAULT_EDGE_WEIGHT;
    }

    /**
     * Removes all the edges in this graph
     *
     * @param edges
     * @return
     */
    protected boolean removeAllEdges(Collection<E> edges) {
        boolean modified = false;

        for (int i = 0; i < edges.size(); i++) {
            modified &= removeEdge((E) edges.toArray()[i]);
        }

        return modified;
    }

    protected String getStringRepresentation(
            Collection<? extends V> vertices,
            Collection<? extends E> edges,
            boolean directed) {
        List<String> renderedEdges = new ArrayList<>();

        StringBuilder sb = new StringBuilder();
        for (E e : edges) {
            if (!(e.getClass().isAssignableFrom(Edge.class))
                    && !(e.getClass().isAssignableFrom(WeightedEdge.class))) {
                sb.append(e.toString());
                sb.append("=");
            }
            if (directed) {
                sb.append("(");
            } else {
                sb.append("{");
            }
            sb.append(getSourceVertexForEdge(e));
            sb.append(",");
            sb.append(getTargetVertexForEdge(e));
            if (directed) {
                sb.append(")");
            } else {
                sb.append("}");
            }

            renderedEdges.add(sb.toString());
            sb.setLength(0);
        }

        return "(" + vertices + ", " + renderedEdges + ")";
    }

    @Override
    public int hashCode() {
        int hash = getAllVertices().hashCode();

        for (E e : getAllEdges()) {
            int part = e.hashCode();

            int source = getSourceVertexForEdge(e).hashCode();
            int target = getTargetVertexForEdge(e).hashCode();

            int pairing =
                    ((source + target)
                            * (source + target + 1) / 2) + target;
            part = (31 * part) + pairing;

            long weight = (long) getEdgeWeight(e);
            part = (31 * part) + (int) (weight ^ (weight >>> 32));

            hash += part;
        }

        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj == null) || !(getClass().isAssignableFrom(obj.getClass()))) {
            return false;
        }

        Graph<V, E> g = TypeUtil.uncheckedCast(obj, null);

        if (!getAllVertices().equals(g.getAllVertices())) {
            return false;
        }
        if (getAllEdges().size() != g.getAllEdges().size()) {
            return false;
        }

        for (E e : getAllEdges()) {
            V source = getSourceVertexForEdge(e);
            V target = getTargetVertexForEdge(e);

            if (!g.containsEdge(e)) {
                return false;
            }

            if (!g.getSourceVertexForEdge(e).equals(source)
                    || !g.getTargetVertexForEdge(e).equals(target)) {
                return false;
            }

            if (Math.abs(getEdgeWeight(e) - g.getEdgeWeight(e)) > 10e-7) {
                return false;
            }
        }

        return true;
    }

    /**
     * Adds the edge to its source and target vertices.
     *
     * @param e
     */
    protected abstract void addEdgeToTouchingVertices(E e);

    /**
     * remove the edge from touching vertices
     *
     * @param e
     */
    protected abstract void removeEdgeFromTouchingVertices(E e);

    protected VertexEdgeContainer<E> getEdgeContainer(V vertex, Class clazz) {
        VertexEdgeContainer<E> ec = vertexMap.get(vertex);

        if (ec == null) {
            try {
                ec = (VertexEdgeContainer<E>) clazz.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                throw new VertexEdgeContainerCreationException(clazz.getName());
            }
            vertexMap.put(vertex, ec);
        }

        return ec;
    }

    static Supplier<Edge> edgeFactory = new Supplier<Edge>() {
        @Override
        public Edge get() {
            return new Edge();
        }
    };

    protected Edge getEdgeFromFactory() {
        return edgeFactory.get();
    }

    private Edge createEdge(E e, V sourceVertex, V targetVertex) {
        Edge edge;
        if (e instanceof Edge) {
            edge = (Edge) e;
        } else {
            edge = TypeUtil.uncheckedCast(getEdgeFromFactory(), null);
        }
        edge.setSource(sourceVertex);
        edge.setTarget(targetVertex);
        return edge;
    }

    private Edge getEdge(E e) {
        if (e instanceof Edge) {
            return (Edge) e;
        }

        return edgeMap.get(e);
    }
}