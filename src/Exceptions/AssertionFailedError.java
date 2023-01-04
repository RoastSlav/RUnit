package Exceptions;

public class AssertionFailedError extends RuntimeException {
    public AssertionFailedError(String message) {
        super(message);
    }

    public AssertionFailedError(String message, Throwable cause) {
        super(message, cause);
    }

    public AssertionFailedError(Throwable cause) {
        super(cause);
    }
}
