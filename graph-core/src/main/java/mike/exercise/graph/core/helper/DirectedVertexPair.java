package mike.exercise.graph.core.helper;

import java.util.Collection;
import java.util.LinkedList;

/**
 * vertex pair with restricted connecting direction
 */
public class DirectedVertexPair<V> extends VertexPair<V> {
    public DirectedVertexPair(V value1, V value2) {
        super(value1, value2);
    }

    public DirectedVertexPair(Collection<V> vs) {
        super(vs);
        if (vs.getClass().isAssignableFrom(LinkedList.class)) {
            throw new IllegalArgumentException("Only linked list is acceptable!");
        }
    }
}


