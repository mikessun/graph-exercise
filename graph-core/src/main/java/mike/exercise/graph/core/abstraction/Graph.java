/*
 * Created on Oct 17, 2005
 *
 * Copyright (c) 2005, The JUNG Authors 
 *
 * All rights reserved.
 *
 * This software is open-source under the BSD license; see either
 * "license.txt" or
 * https://github.com/jrtom/jung/blob/master/LICENSE for a description.
 */
package mike.exercise.graph.core.abstraction;

import java.io.Serializable;
import java.util.Collection;


/**
 * Graph abstraction
 */
public interface Graph<V, E> extends Serializable {

    /**
     * add an edge to two vertices
     *
     * @param sourceVertex
     * @param targetVertex
     * @param e
     * @return
     */
    public boolean addEdge(V sourceVertex, V targetVertex, E e);

    /**
     * add a vertex
     *
     * @param v
     * @return
     */
    public boolean addVertex(V v);

    /**
     * remove an edge
     *
     * @param e
     * @return
     */
    public boolean removeEdge(E e);

    /**
     * remove a vertex
     *
     * @param v
     * @return
     */
    public boolean removeVertex(V v);

    /**
     * remove vertices
     *
     * @param v
     * @return
     */
    public boolean removeVertices(Collection<? extends V> v);

    /**
     * check if two vertices are connected
     *
     * @param v1
     * @param v2
     * @return
     */
    public boolean isVerticesConnected(V v1, V v2);

    /**
     * get the connected vertices for a vertex
     *
     * @param v
     * @return
     */
    public Collection<V> getConnectedVertices(V v);

    /**
     * get the vertices for an edge
     *
     * @param e
     * @return
     */
    public Collection<V> getVertices(E e);

    /**
     * get the edges for an vertex
     *
     * @param e
     * @return
     */
    public Collection<E> getEdges(V v);

    /**
     * get the edge to link  the two vertices
     *
     * @param v1
     * @param v2
     * @return
     */
    public E getEdges(V v1, V v2);

    /**
     * remove listed edges
     *
     * @param edges
     * @return
     */
    public boolean removeEdges(Collection<? extends E> edges);

    /**
     * remove edge to connect two vertices
     *
     * @param v1
     * @param v2
     * @return
     */
    public boolean removeEdgeForVertices(V v1, V v2);

    /**
     * get all vertices in this graph
     */
    Collection<V> getAllVertices();


    /**
     * get all edges in this graph
     */
    Collection<E> getAllEdges();

    /**
     * check if this graph contains teh vertex
     */
    public boolean containsVertex(V v);

    /**
     * get the source vertex of an edge
     *
     * @param e
     * @return
     */
    public V getSourceVertexForEdge(E e);

    /**
     * get the source vertex of an edge
     *
     * @param e
     * @return
     */
    public V getTargetVertexForEdge(E e);

    /**
     * get the weight of an edge
     *
     * @param e
     * @return
     */
    public double getEdgeWeight(E e);


    /**
     * check if the graph contains an edge
     *
     * @param e
     * @return
     */
    public boolean containsEdge(E e);

    /**
     * partition a graph to a required set of subgraphs
     *
     * @param numberOfPartitions
     * @return
     *//*
    public Collection<Graph<V, E>> partitionGraph(Graph<V, E> graph, int numberOfPartitions);*/

}