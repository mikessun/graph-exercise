package mike.exercise.graph.core.exception;

/**
 * vertex not existing exception
 */
public class VertexEdgeContainerCreationException extends RuntimeException {

    public VertexEdgeContainerCreationException() {
        super();
    }

    public VertexEdgeContainerCreationException(String message) {
        super(message);
    }

    public VertexEdgeContainerCreationException(String message, Throwable cause) {
        super(message, cause);
    }

    public VertexEdgeContainerCreationException(Throwable cause) {
        super(cause);
    }
}
