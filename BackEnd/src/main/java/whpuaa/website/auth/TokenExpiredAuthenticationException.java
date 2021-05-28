package whpuaa.website.auth;

import org.springframework.security.core.AuthenticationException;

public class TokenExpiredAuthenticationException extends AuthenticationException {
    public TokenExpiredAuthenticationException() {
        super("Token is expired.");
    }

    public TokenExpiredAuthenticationException(String msg) {
        super(msg);
    }

    public TokenExpiredAuthenticationException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
