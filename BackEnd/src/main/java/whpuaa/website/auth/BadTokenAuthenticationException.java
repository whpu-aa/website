package whpuaa.website.auth;

import org.springframework.security.core.AuthenticationException;

public class BadTokenAuthenticationException extends AuthenticationException {
    public BadTokenAuthenticationException() {
        super("Token is not valid.");
    }

    public BadTokenAuthenticationException(String msg) {
        super(msg);
    }

    public BadTokenAuthenticationException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
