package mike.exercise.graph.core.impl;

import mike.exercise.graph.ResultWrapper;
import mike.exercise.graph.TestUtil;
import mike.exercise.graph.core.interfaces.Graph;
import mike.exercise.graph.core.interfaces.UndirectedGraph;
import mike.exercise.graph.core.util.TypeUtil;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import static org.junit.Assert.assertTrue;

/**
 * Tests serialization and deserialization of Graph objects.
 */
public class GraphSerializationTest {
    Graph graph;

    @Before
    public void before(){
        graph=TestUtil.undirectedGraphFactory.get();
    }

    @Test
    public void testGraphSerialization() throws Exception {
        ResultWrapper resultWrapper = TestUtil.buildUndirectedTestVertexGraph(graph);

        UndirectedGraph deserializedGraph = TypeUtil.uncheckedCast(serializeAndDeserialize(graph), null);

        assertTrue(deserializedGraph.containsVertex(resultWrapper.getV()[1]));
        assertTrue(deserializedGraph.containsVertex(resultWrapper.getV()[0]));
        assertTrue(deserializedGraph.containsEdge(resultWrapper.getE()));
    }

    private Object serializeAndDeserialize(Object obj)
            throws Exception {
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(bout);

        out.writeObject(obj);
        out.flush();

        ByteArrayInputStream bin = new ByteArrayInputStream(bout.toByteArray());
        ObjectInputStream in = new ObjectInputStream(bin);

        obj = in.readObject();
        return obj;
    }
}
