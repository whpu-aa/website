package whpuaa.website.token;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.keygen.KeyGenerators;
import whpuaa.website.user.BadCredentialException;
import whpuaa.website.user.UserInfo;
import whpuaa.website.user.UserNotExistException;
import whpuaa.website.user.UserService;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;

import static org.mockito.BDDMockito.*;
import static org.assertj.core.api.Assertions.*;


@SpringBootTest
public class TokenServiceTests {
    @MockBean
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @Test
    public void createAndVerifyTokenShouldWork() throws UserNotExistException, BadCredentialException, BadTokenException, TokenExpiredException {
        UserInfo mockUser = new UserInfo(1, "username", "", "", new ArrayList<>(), new HashMap<>());

        given(userService.verifyUserCredential("username", "password")).willReturn(mockUser);

        TokenService.CreateTokenResult result = tokenService.createToken("username", "password", Duration.ofDays(30));

        assertThat(result.user).isEqualTo(mockUser);
        assertThat(result.token).isNotBlank();

        verify(userService, times(1)).verifyUserCredential("username", "password");

        given(userService.getUser(1)).willReturn(mockUser);

        UserInfo u = tokenService.verifyToken(result.token);

        assertThat(u).isEqualTo(mockUser);

        verify(userService, times(1)).getUser(1);
    }

    @Test
    public void revokeTokenShouldWork() throws UserNotExistException, BadCredentialException {
        UserInfo mockUser = new UserInfo(1, "username", "", "", new ArrayList<>(), new HashMap<>());

        given(userService.verifyUserCredential("username", "password")).willReturn(mockUser);

        TokenService.CreateTokenResult result = tokenService.createToken("username", "password", Duration.ofDays(30));

        assertThat(result.user).isEqualTo(mockUser);
        assertThat(result.token).isNotBlank();

        verify(userService, times(1)).verifyUserCredential("username", "password");

        tokenService.revokeToken(result.token);

        assertThatThrownBy(() -> tokenService.verifyToken(result.token)).isInstanceOf(BadTokenException.class);
    }

    @Test
    public void createTokenThrowBadCredentialException() throws BadCredentialException, UserNotExistException {
        given(userService.verifyUserCredential("username", "password")).willThrow(BadCredentialException.class);

        assertThatThrownBy(() -> tokenService.createToken("username", "password", Duration.ofDays(30)))
                .isInstanceOf(BadCredentialException.class);
    }

    @Test
    public void createTokenThrowUserNotExistException() throws BadCredentialException, UserNotExistException {
        given(userService.verifyUserCredential("username", "password")).willThrow(UserNotExistException.class);

        assertThatThrownBy(() -> tokenService.createToken("username", "password", Duration.ofDays(30)))
                .isInstanceOf(UserNotExistException.class);
    }

    @Test
    public void verifyTokenThrowBadTokenException() {
        assertThatThrownBy(() -> tokenService.verifyToken(KeyGenerators.string().generateKey())).isInstanceOf(BadTokenException.class);
    }

    @Test
    public void verifyTokenThrowTokenExpiredException() throws BadCredentialException, UserNotExistException, InterruptedException {
        UserInfo mockUser = new UserInfo(1, "username", "", "", new ArrayList<>(), new HashMap<>());

        given(userService.verifyUserCredential("username", "password")).willReturn(mockUser);
        TokenService.CreateTokenResult result = tokenService.createToken("username", "password", Duration.ofMillis(10));

        Thread.sleep(100);

        assertThatThrownBy(() -> tokenService.verifyToken(result.token)).isInstanceOf(TokenExpiredException.class);
    }
}
