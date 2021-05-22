package whpuaa.website.user;

/**
 * Thrown when the new username already exists in another user.
 */
public class UsernameConflictException extends Exception {
    public UsernameConflictException() {
    }

    public UsernameConflictException(String message) {
        super(message);
    }

    public UsernameConflictException(String message, Throwable cause) {
        super(message, cause);
    }

    public UsernameConflictException(Throwable cause) {
        super(cause);
    }

    public UsernameConflictException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
