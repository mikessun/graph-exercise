package mike.exercise.graph.algorithm.path;

import mike.exercise.graph.core.interfaces.Graph;

import java.util.*;

/**
 * Dijstra shortest path algorithm
 *
 * @param <V>
 * @param <E>
 */

public final class DijkstraShortestPath<V, E> {
    private Graph graph;
    private final Collection<V> nodes;
    private final Collection<E> edges;
    private Collection<V> settledNodes;
    private Collection<V> unSettledNodes;
    private Map<V, V> predecessors;
    private Map<V, Double> distance;

    public DijkstraShortestPath(Graph graph) {
        this.graph = graph;
        this.nodes = new ArrayList<V>(graph.getAllVertices());
        this.edges = new ArrayList<E>(graph.getAllEdges());
    }

    private void execute(V source) {
        settledNodes = new HashSet<V>();
        unSettledNodes = new HashSet<V>();
        distance = new HashMap<>();
        predecessors = new HashMap<V, V>();
        distance.put(source, (double) 0);
        unSettledNodes.add(source);
        while (unSettledNodes.size() > 0) {
            V node = getMinimum(unSettledNodes);
            settledNodes.add(node);
            unSettledNodes.remove(node);
            findMinimalDistances(node);
        }
    }

    private void findMinimalDistances(V node) {
        Collection<V> adjacentNodes = graph.getConnectedVertices(node);
        for (V target : adjacentNodes) {
            double distance = graph.getEdgeWeight(graph.getEdges(node, target));
            if (getShortestDistance(target) > getShortestDistance(node)
                    + distance) {
                this.distance.put(target, getShortestDistance(node)
                        + distance);
                predecessors.put(target, node);
                unSettledNodes.add(target);
            }
        }
    }

    private V getMinimum(Collection<V> Ves) {
        V minimum = null;
        for (V V : Ves) {
            if (minimum == null) {
                minimum = V;
            } else {
                if (getShortestDistance(V) < getShortestDistance(minimum)) {
                    minimum = V;
                }
            }
        }
        return minimum;
    }

    private boolean isSettled(V V) {
        return settledNodes.contains(V);
    }

    private double getShortestDistance(V destination) {
        Double d = distance.get(destination);
        if (d == null) {
            return Integer.MAX_VALUE;
        } else {
            return d;
        }
    }

    /*
     * get the shortest path from vertex source to target
     */
    public LinkedList<V> getPath(V source, V target) {
        this.execute(source);
        LinkedList<V> path = new LinkedList<V>();
        V step = target;
        if (predecessors.get(step) == null) {
            return null;
        }
        path.add(step);
        while (predecessors.get(step) != null) {
            step = predecessors.get(step);
            path.add(step);
        }
        Collections.reverse(path);
        return path;
    }

}
