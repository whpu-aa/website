package whpuaa.website.user;

/**
 * Thrown when username or password is wrong.
 */
public class BadCredentialException extends Exception {
    public BadCredentialException() {
    }

    public BadCredentialException(String message) {
        super(message);
    }

    public BadCredentialException(String message, Throwable cause) {
        super(message, cause);
    }

    public BadCredentialException(Throwable cause) {
        super(cause);
    }

    public BadCredentialException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
