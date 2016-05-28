package mike.exercise.graph.core.helper;

import java.io.Serializable;
import java.util.Iterator;

/**
 * Pair type to hold two connected vertices
 */
public interface Pair<T> extends Serializable {

    public T getFirst();

    public T getSecond();

    public Iterator<T> iterator();

    public int size();

    public Object[] toArray();

}
