package mike.exercise.graph;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * wrapper to hold the 2 vertices and the edge linking them
 *
 * @param <V>
 * @param <E>
 */
@AllArgsConstructor
public class ResultWrapper<V, E> {
    @Getter
    private V v[];
    @Getter
    private E e;
}

