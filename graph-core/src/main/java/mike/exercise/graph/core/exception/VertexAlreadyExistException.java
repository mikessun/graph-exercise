package mike.exercise.graph.core.exception;

/**
 * vertex existing exception
 */
public class VertexAlreadyExistException extends RuntimeException {

    public VertexAlreadyExistException() {
        super();
    }

    public VertexAlreadyExistException(String message) {
        super(message);
    }

    public VertexAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public VertexAlreadyExistException(Throwable cause) {
        super(cause);
    }
}
