package mike.exercise.graph.core.helper;

import mike.exercise.graph.core.abstraction.Pair;
import org.apache.commons.lang3.ArrayUtils;

import java.util.Collection;
import java.util.Iterator;

/**
 * Generic vertex pair type
 */
public class VertexPair<T> implements Pair<T> {
    private T first;
    private T second;

    public VertexPair(T value1, T value2) {
        if (value1 == null || value2 == null)
            throw new IllegalArgumentException("VertexPair cannot contain null values");
        init(value1, value2);
    }

    private void init(T value1, T value2) {
        first = value1;
        second = value2;
    }

    /**
     * Creates a VertexPair from the passed Collection.
     *
     * @param values
     */
    public VertexPair(Collection<? extends T> values) {
        if (values == null)
            throw new IllegalArgumentException("Input collection cannot be null");
        if (values.size() == 2) {
            if (values.contains(null))
                throw new IllegalArgumentException("VertexPair cannot contain null values");
            Iterator<? extends T> iter = values.iterator();
            init(iter.next(), iter.next());
        } else
            throw new IllegalArgumentException("VertexPair may only be created from a Collection of exactly 2 elements");

    }

    /**
     * Creates a vertice pair from the passed array.
     *
     * @param values
     */
    public VertexPair(T[] values) {
        if (ArrayUtils.isEmpty(values)) {
            throw new IllegalArgumentException("Empty vertices!");
        }
        if (values.length != 2) {
            throw new IllegalArgumentException("Only 2 vertices are allowed!");
        }

        if (values[0] == null || values[1] == null) {
            throw new IllegalArgumentException("Vertex can not null!");
        }
        init(values[0], values[1]);
    }

    public T getFirst() {
        return first;
    }

    public T getSecond() {
        return second;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;

        if (o instanceof VertexPair) {
            VertexPair otherPair = (VertexPair) o;
            Object otherFirst = otherPair.getFirst();
            Object otherSecond = otherPair.getSecond();
            return
                    (this.first == otherFirst ||
                            (this.first != null && this.first.equals(otherFirst)))
                            &&
                            (this.second == otherSecond ||
                                    (this.second != null && this.second.equals(otherSecond)));
        } else {
            return false;
        }
    }

    public int hashCode() {
        int hashCode = 1;
        hashCode = 34 * hashCode + (first == null ? 0 : first.hashCode());
        hashCode += 35 * hashCode + (second == null ? 0 : second.hashCode());
        return hashCode;
    }

    public String toString() {
        return "<" + first.toString() + ", " + second.toString() + ">";
    }

    public boolean contains(Object o) {
        return (first == o || first.equals(o) || second == o || second.equals(o));
    }

    public boolean containsAll(Collection<?> c) {
        if (c.size() > 2)
            return false;
        Iterator<?> iter = c.iterator();
        Object c_first = iter.next();
        Object c_second = iter.next();
        return this.contains(c_first) && this.contains(c_second);
    }

    public boolean isEmpty() {
        return false;
    }

    public Iterator<T> iterator() {
        return new PairIterator();
    }

    public int size() {
        return 2;
    }

    public Object[] toArray() {
        Object[] to_return = new Object[2];
        to_return[0] = first;
        to_return[1] = second;
        return to_return;
    }

    private class PairIterator implements Iterator<T> {
        int position;

        private PairIterator() {
            position = 0;
        }

        public boolean hasNext() {
            return position < 2;
        }

        public T next() {
            position++;
            if (position == 1)
                return first;
            else if (position == 2)
                return second;
            else
                return null;
        }
    }
}


