package mike.exercise.graph.core.exception;

/**
 * vertex not existing exception
 */
public class NotWeightEdgeException extends RuntimeException {

    public NotWeightEdgeException() {
        super();
    }

    public NotWeightEdgeException(String message) {
        super(message);
    }

    public NotWeightEdgeException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotWeightEdgeException(Throwable cause) {
        super(cause);
    }
}
