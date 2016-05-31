package mike.exercise.graph.algorithm.common;

import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

/**
 * partition result
 */
public class PartitionResult {
    @Getter@Setter
    private Collection partitions;

    @Getter@Setter
    private double cutCost;


}
