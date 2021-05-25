package whpuaa.website.user;

/**
 * Thrown when do some illegal operation on root user like deleting it or changing its permission.
 */
public class InvalidOperationOnRootUserException extends Exception {
    public InvalidOperationOnRootUserException() {
    }

    public InvalidOperationOnRootUserException(String message) {
        super(message);
    }

    public InvalidOperationOnRootUserException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidOperationOnRootUserException(Throwable cause) {
        super(cause);
    }

    public InvalidOperationOnRootUserException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
