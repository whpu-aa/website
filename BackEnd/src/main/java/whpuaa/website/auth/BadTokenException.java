package whpuaa.website.auth;

import org.springframework.security.core.AuthenticationException;

public class BadTokenException extends AuthenticationException {
    public BadTokenException() {
        super("Token is not valid.");
    }

    public BadTokenException(String msg) {
        super(msg);
    }

    public BadTokenException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
