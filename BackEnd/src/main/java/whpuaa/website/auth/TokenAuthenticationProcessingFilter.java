package whpuaa.website.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import whpuaa.website.controller.model.HttpCommonErrorResponse;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class TokenAuthenticationProcessingFilter extends OncePerRequestFilter {
    public static final String TOKEN_QUERY_PARAM_NAME = "access_token";
    public static final String TOKEN_HEADER_NAME = "Authorize";
    public static final String TOKEN_HEADER_START = "Bearer ";

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        TokenAuthentication authentication = null;

        String tokenQueryParam = request.getParameter(TOKEN_QUERY_PARAM_NAME);
        if (tokenQueryParam != null) {
            authentication = new TokenAuthentication();
            authentication.setToken(tokenQueryParam);
        }

        if (authentication == null) {
            String tokenHeaderValue = request.getHeader(TOKEN_HEADER_NAME);
            if (tokenHeaderValue != null && tokenHeaderValue.startsWith(TOKEN_HEADER_START)) {
                authentication = new TokenAuthentication();
                authentication.setToken(tokenHeaderValue.substring(TOKEN_HEADER_START.length()));
            }
        }

        if (authentication != null) {
            try {
                Authentication auth = authenticationManager.authenticate(authentication);
                SecurityContextHolder.getContext().setAuthentication(auth);
            } catch (AuthenticationException e) {
                response.setStatus(401);
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);

                byte[] buffer = objectMapper.writeValueAsBytes(new HttpCommonErrorResponse(100000, e.getMessage()));

                response.setContentLength(buffer.length);
                response.getOutputStream().write(buffer);
                return;
            }
        }

        filterChain.doFilter(request, response);
    }
}
