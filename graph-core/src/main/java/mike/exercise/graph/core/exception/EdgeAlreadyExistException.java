package mike.exercise.graph.core.exception;

/**
 * vertex not existing exception
 */
public class EdgeAlreadyExistException extends RuntimeException {

    public EdgeAlreadyExistException() {
        super();
    }

    public EdgeAlreadyExistException(String message) {
        super(message);
    }

    public EdgeAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public EdgeAlreadyExistException(Throwable cause) {
        super(cause);
    }
}
