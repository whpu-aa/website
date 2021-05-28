package whpuaa.website.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import whpuaa.website.token.BadTokenException;
import whpuaa.website.token.TokenExpiredException;
import whpuaa.website.token.TokenService;
import whpuaa.website.user.UserInfo;

@Component
public class TokenAuthenticationManager implements AuthenticationManager {

    @Autowired
    private TokenService tokenService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        TokenAuthentication tokenAuthentication = (TokenAuthentication) authentication;

        String token = tokenAuthentication.getToken();

        try {
            UserInfo user = tokenService.verifyToken(token);
            tokenAuthentication.setUser(user);
            tokenAuthentication.setAuthenticated(true);
            return tokenAuthentication;
        } catch (BadTokenException e) {
            throw new BadTokenAuthenticationException("Token is invalid.", e);
        } catch (TokenExpiredException e) {
            throw new TokenExpiredAuthenticationException("Token is expired.", e);
        }
    }
}
