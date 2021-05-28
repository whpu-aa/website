package whpuaa.website.auth;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import whpuaa.website.token.BadTokenException;
import whpuaa.website.token.TokenExpiredException;
import whpuaa.website.token.TokenService;
import whpuaa.website.user.UserInfo;

import java.util.ArrayList;
import java.util.HashMap;

import static org.mockito.BDDMockito.*;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class TokenAuthenticationManagerTests {
    @MockBean
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Test
    public void authenticateShouldWork() throws TokenExpiredException, BadTokenException {
        UserInfo mockUser = new UserInfo(1, "username", "", "", new ArrayList<>(), new HashMap<>());
        mockUser.getPermission().add("a-permission");
        mockUser.getDetails().put("a-key", "a-value");

        given(tokenService.verifyToken("a-token")).willReturn(mockUser);

        TokenAuthentication authentication = new TokenAuthentication();
        authentication.setToken("a-token");

        Authentication auth0 = authenticationManager.authenticate(authentication);

        assertThat(auth0).isInstanceOf(TokenAuthentication.class);
        TokenAuthentication auth = (TokenAuthentication) auth0;

        assertThat(auth.getUser()).isEqualTo(mockUser);
        assertThat(auth.getName()).isEqualTo(mockUser.getUsername());
        assertThat(auth.getPrincipal()).isEqualTo(mockUser);
        assertThat(auth.getDetails()).isEqualTo(mockUser.getDetails());
        assertThat(auth.isAuthenticated()).isTrue();
        assertThat(auth.getAuthorities()).hasSize(1);
        assertThat(auth.getAuthorities().iterator().next().getAuthority()).isEqualTo("a-permission");
    }

    @Test
    public void authenticateThrowBadTokenAuthenticationException() throws TokenExpiredException, BadTokenException {
        given(tokenService.verifyToken("a-token")).willThrow(BadTokenException.class);

        TokenAuthentication authentication = new TokenAuthentication();
        authentication.setToken("a-token");

        assertThatThrownBy(() -> authenticationManager.authenticate(authentication)).isInstanceOf(BadTokenAuthenticationException.class);
    }

    @Test
    public void authenticateThrowTokenExpiredAuthenticationException() throws TokenExpiredException, BadTokenException {
        given(tokenService.verifyToken("a-token")).willThrow(TokenExpiredException.class);

        TokenAuthentication authentication = new TokenAuthentication();
        authentication.setToken("a-token");

        assertThatThrownBy(() -> authenticationManager.authenticate(authentication)).isInstanceOf(TokenExpiredAuthenticationException.class);
    }
}
