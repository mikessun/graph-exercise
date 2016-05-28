package mike.exercise.graph.core.util;

/**
 * Type related utilities
 */
public class TypeUtil<T> {

    private TypeUtil() {
    }

    /**
     * Casts an object to a type
     *
     * @param object
     * @param typeUtil
     * @param <T>
     * @return
     */
    public static <T> T uncheckedCast(Object object, TypeUtil<T> typeUtil) {
        return (T) object;
    }
}

