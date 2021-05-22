package whpuaa.website.token;

import whpuaa.website.user.BadCredentialException;
import whpuaa.website.user.UserInfo;

public class TokenServiceImpl implements TokenService {
    @Override
    public CreateTokenResult createToken(String username, String password) throws BadCredentialException {
        // TODO(crupest): Implement this.
        return null;
    }

    @Override
    public UserInfo verifyToken(String token) throws BadTokenException {
        // TODO(crupest): Implement this.
        return null;
    }

    @Override
    public void revokeToken(String token) {
        // TODO(crupest): Implement this.
    }
}
