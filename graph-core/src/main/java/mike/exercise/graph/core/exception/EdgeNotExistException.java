package mike.exercise.graph.core.exception;

/**
 * vertex not existing exception
 */
public class EdgeNotExistException extends RuntimeException {

    public EdgeNotExistException() {
        super();
    }

    public EdgeNotExistException(String message) {
        super(message);
    }

    public EdgeNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public EdgeNotExistException(Throwable cause) {
        super(cause);
    }
}
