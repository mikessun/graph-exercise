package mike.exercise.graph.core.exception;

/**
 * vertex not existing exception
 */
public class VertexNotExistException extends RuntimeException {

    public VertexNotExistException() {
        super();
    }

    public VertexNotExistException(String message) {
        super(message);
    }

    public VertexNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public VertexNotExistException(Throwable cause) {
        super(cause);
    }
}
