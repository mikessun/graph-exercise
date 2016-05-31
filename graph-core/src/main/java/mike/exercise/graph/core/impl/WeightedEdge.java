package mike.exercise.graph.core.impl;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import mike.exercise.graph.core.interfaces.WeightedGraph;

/**
 * Weighted edge type
 */
public class WeightedEdge
        extends Edge {
    private static final long serialVersionUID = -439708706467350994L;

    @Setter(AccessLevel.PACKAGE)
    @Getter(AccessLevel.PROTECTED)
    double weight = WeightedGraph.DEFAULT_EDGE_WEIGHT;
}
