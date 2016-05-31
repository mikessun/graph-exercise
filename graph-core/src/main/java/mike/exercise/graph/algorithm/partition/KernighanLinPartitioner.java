package mike.exercise.graph.algorithm.partition;

import mike.exercise.graph.algorithm.common.PartitionResult;
import mike.exercise.graph.core.interfaces.Graph;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;
import java.util.LinkedList;
import java.util.function.Supplier;

/**
 * Basic KernighanLinPartitioner algorithm to partition a graph to two
 */
public class KernighanLinPartitioner<V, E> {
    private Collection<V> partition1, partition2;
    private Collection<V> unswapped1, unswapped2;
   private Collection<Collection> partitions;

    private Graph<V, E> graph;

    private int partitionSize;

    public static Supplier<KernighanLinPartitioner> kernighanLinFactory = new Supplier<KernighanLinPartitioner>() {
        @Override
        public KernighanLinPartitioner get() {
            return new KernighanLinPartitioner();
        }
    };

    public KernighanLinPartitioner() {
        partition1 = new ArrayList<>();
        partition2 = new ArrayList<>();
    }

    /**
     * Performs KerninghanLin on the given graph
     **/
    public PartitionResult partition(Graph<V, E> graph) {
        this.graph = graph;
        if (graph.getAllVertices().size() % 2 != 0) {
            throw new RuntimeException("Size of vertices must be even");
        }

        this.partitionSize = this.graph.getAllVertices().size() / 2;

        int i = 0;
        for (V v : graph.getAllVertices()) {
            (++i > partitionSize ? partition2 : partition1).add(v);
        }
        unswapped1 = new ArrayList<>(partition1);
        unswapped2 = new ArrayList<>(partition2);

        doAllSwaps();

        Collection<Collection> partitions = new ArrayList<>(2);
        partitions.add(partition1);
        partitions.add(partition2);

        PartitionResult partitionResult=new PartitionResult();
        partitionResult.setPartitions(partitions);
        partitionResult.setCutCost(this.getCutCost());
        return partitionResult;
    }

    private double getCutCost() {
        double cost = 0;

        for (E edge : graph.getAllEdges()) {
            if (partition1.contains(graph.getSourceVertexForEdge(edge)) != partition1.contains(graph.getTargetVertexForEdge(edge))) {
                cost += graph.getEdgeWeight(edge);
            }
        }
        return cost;
    }

    private void doAllSwaps() {

        LinkedList<Pair<V, V>> swaps = new LinkedList<Pair<V, V>>();
        double minCost = Double.POSITIVE_INFINITY;
        int minId = -1;

        for (int i = 0; i < partitionSize; i++) {
            double cost = doSingleSwap(swaps);
            if (cost < minCost) {
                minCost = cost;
                minId = i;
            }
        }

        while (swaps.size() - 1 > minId) {
            Pair<V, V> pair = swaps.pop();
            swapVertices(partition1, pair.getRight(), partition2, pair.getLeft());
        }
    }

    private double doSingleSwap(Deque<Pair<V, V>> swaps) {

        Pair<V, V> maxPair = null;
        double maxGain = Double.NEGATIVE_INFINITY;

        for (V vertex1 : unswapped1) {
            for (V vertex2 : unswapped2) {

                E e = graph.getEdges(vertex1, vertex2);
                double edgeCost = (e != null) ? graph.getEdgeWeight(e) : 0;

                double gain = getVertexCost(vertex1) + getVertexCost(vertex2) - 2 * edgeCost;

                if (gain > maxGain) {
                    maxPair = new ImmutablePair<V, V>(vertex1, vertex2);
                    maxGain = gain;
                }

            }
        }

        swapVertices(partition1, maxPair.getLeft(), partition2, maxPair.getRight());
        swaps.push(maxPair);
        unswapped1.remove(maxPair.getLeft());
        unswapped2.remove(maxPair.getRight());

        return getCutCost();
    }

    private double getVertexCost(V v) {
        double cost = 0;

        for (V v2 : graph.getConnectedVertices(v)) {
            E edge = graph.getEdges(v, v2);

            if (partition1.contains(v) != partition1.contains(v2))
                cost += graph.getEdgeWeight(edge);
            else
                cost -= graph.getEdgeWeight(edge);
        }
        return cost;
    }

    private void swapVertices(Collection<V> originalVertice, V va, Collection<V> targetVertice, V vb) {
        if (!originalVertice.contains(va) || originalVertice.contains(vb) ||
                !targetVertice.contains(vb) || targetVertice.contains(va)) throw new RuntimeException("Invalid swap");
        originalVertice.remove(va);
        originalVertice.add(vb);
        targetVertice.remove(vb);
        targetVertice.add(va);
    }


}
