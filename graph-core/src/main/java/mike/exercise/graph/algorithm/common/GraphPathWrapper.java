package mike.exercise.graph.algorithm.common;

import lombok.Getter;
import mike.exercise.graph.core.abstraction.Graph;

import java.util.List;

/**
 * wrapper class to hold all path releated data
 */
public class GraphPathWrapper<V, E> {
    @Getter
    private Graph<V, E> graph;

    @Getter
    private List<E> edgeList;

    @Getter
    private V startVertex;

    @Getter
    private V endVertex;

    @Getter
    private double weight;

    public GraphPathWrapper(Graph<V, E> graph, V startVertex, V endVertex, List<E> edgeList, double weight) {
        this.graph = graph;
        this.startVertex = startVertex;
        this.endVertex = endVertex;
        this.edgeList = edgeList;
        this.weight = weight;
    }
}
