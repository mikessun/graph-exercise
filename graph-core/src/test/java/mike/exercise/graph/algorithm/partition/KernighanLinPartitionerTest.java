package mike.exercise.graph.algorithm.partition;

import mike.exercise.graph.TestUtil;
import mike.exercise.graph.algorithm.common.PartitionResult;
import mike.exercise.graph.core.interfaces.Graph;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for KL partitioning
 */
public class KernighanLinPartitionerTest {
    private Graph graph;

    @Before
    public void before() {
        graph = TestUtil.undirectedGraphFactory.get();
        String[] vertex = new String[4];
        Integer[] edge = new Integer[4];
        for (int i = 0; i < vertex.length; i++) {
            vertex[i] = TestUtil.vertexFactory.get();
            graph.addVertex(vertex[i]);
        }

        for (int i = 0; i < edge.length; i++) {
            edge[i] = TestUtil.edgeFactory.get();
        }

        graph.addEdge(vertex[0], vertex[1], edge[0]);
        graph.addEdge(vertex[2], vertex[1], edge[1]);
        graph.addEdge(vertex[2], vertex[3], edge[2]);
        graph.addEdge(vertex[0], vertex[3], edge[3]);
    }

    @Test
    public void testProcess() throws Exception {
        KernighanLinPartitioner kernighanLin = KernighanLinPartitioner.kernighanLinFactory.get();
        PartitionResult result = kernighanLin.partition(graph);

        Assert.assertNotNull(result);
        Assert.assertTrue("should not be " + result.getCutCost(), result.getCutCost() == 2);
        Assert.assertTrue("should not be " + result.getPartitions(), result.getPartitions().size() == 2);
    }
}