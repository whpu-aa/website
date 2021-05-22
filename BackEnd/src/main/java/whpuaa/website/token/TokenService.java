package whpuaa.website.token;

import whpuaa.website.user.BadCredentialException;
import whpuaa.website.user.UserInfo;

public interface TokenService {
    class CreateTokenResult {
        /**
         * The created token.
         */
        public String token;
        /**
         * The user info.
         */
        public UserInfo user;
    }

    /**
     * Create a token with username and password. If username and password do not match, {@link BadCredentialException} is thrown.
     * @param username The username.
     * @param password The password.
     * @return The result contains token and user info.
     * @throws BadCredentialException Thrown when username or password is wrong.
     */
    CreateTokenResult createToken(String username, String password) throws BadCredentialException;

    /**
     * Validate a token. Throw {@link BadTokenException} if token is invalid. Return the user info of the token.
     * @param token The token to validate.
     * @return The info of the user of the token.
     * @throws BadTokenException Thrown when token is invalid.
     */
    UserInfo verifyToken(String token) throws BadTokenException;

    /**
     * Revoke a token, which means it is not valid any more.
     * @param token The token to revoke.
     */
    void revokeToken(String token);
}
