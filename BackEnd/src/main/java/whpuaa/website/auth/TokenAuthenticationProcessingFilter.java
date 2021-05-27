package whpuaa.website.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class TokenAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter {
    public static final String TOKEN_QUERY_PARAM_NAME = "access_token";
    public static final String TOKEN_HEADER_NAME = "Authorize";
    public static final String TOKEN_HEADER_START = "Bearer ";

    public TokenAuthenticationProcessingFilter(@Autowired AuthenticationManager authenticationManager) {
        super("/api/**", authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        String tokenQueryParam = request.getParameter(TOKEN_QUERY_PARAM_NAME);
        if (tokenQueryParam != null) {
            TokenAuthentication authentication = new TokenAuthentication();
            authentication.setToken(tokenQueryParam);
            return authentication;
        }

        String tokenHeaderValue = request.getHeader(TOKEN_HEADER_NAME);
        if (tokenHeaderValue != null && tokenHeaderValue.startsWith(TOKEN_HEADER_START)) {
            TokenAuthentication authentication = new TokenAuthentication();
            authentication.setToken(tokenHeaderValue.substring(TOKEN_HEADER_START.length()));
            return authentication;
        }

        return null;
    }
}
