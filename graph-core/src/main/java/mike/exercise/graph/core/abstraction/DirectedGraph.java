/* ==========================================
 * JGraphT : a free Java graph-theory library
 * ==========================================
 *
 * Project Info:  http://jgrapht.sourceforge.net/
 * Project Creator:  Barak Naveh (http://sourceforge.net/users/barak_naveh)
 *
 * (C) Copyright 2003-2008, by Barak Naveh and Contributors.
 *
 * This program and the accompanying materials are dual-licensed under
 * either
 *
 * (a) the terms of the GNU Lesser General Public License version 2.1
 * as published by the Free Software Foundation, or (at your option) any
 * later version.
 *
 * or (per the licensee's choosing)
 *
 * (b) the terms of the Eclipse Public License v1.0 as published by
 * the Eclipse Foundation.
 */
/* ------------------
 * DirectedGraphImpl.java
 * ------------------
 * (C) Copyright 2003-2008, by Barak Naveh and Contributors.
 *
 * Original Author:  Barak Naveh
 * Contributor(s):   Christian Hammer
 *
 * $Id$
 *
 * Changes
 * -------
 * 24-Jul-2003 : Initial revision (BN);
 * 11-Mar-2004 : Made generic (CH);
 * 07-May-2006 : Changed from List<Edge> to Set<Edge> (JVS);
 *
 */
package mike.exercise.graph.core.abstraction;

import java.util.Collection;


/**
 * A graph type have all edges directed
 */
public interface DirectedGraph<V, E>
    extends Graph<V, E>
{
    /**
     * Get the number of incoming directed edges from a vertex
     * @param vertex
     * @return
     */
    public int getIncomingEdgeNumber(V vertex);

    /**
     * Returns a set of edges directed to a vertex.
     */
    public Collection<E> getIncomingEdges(V vertex);

    /**
     * Get the number of outgoing edges from a vertex (directed to other vertex)
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
