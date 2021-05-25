package whpuaa.website.token;

import whpuaa.website.user.UserInfo;

public interface TokenService {
    class CreateTokenResult {
        public String token;
        public UserInfo user;
    }

    CreateTokenResult createToken(String username, String password);

    UserInfo verifyToken(String token);

    void revokeToken(String token);
}
