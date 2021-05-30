package whpuaa.website.token;

import whpuaa.website.user.BadCredentialException;
import whpuaa.website.user.UserInfo;
import whpuaa.website.user.UserNotExistException;

import java.time.Duration;
import java.util.Optional;

public interface TokenService {
    class CreateTokenResult {
        public CreateTokenResult() {
        }

        public CreateTokenResult(String token, UserInfo user) {
            this.token = token;
            this.user = user;
        }

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
     *
     * @param username The username.
     * @param password The password.
     * @return The result contains token and user info.
     * @throws UserNotExistException  Thrown when user with given username does not exist.
     * @throws BadCredentialException Thrown when password is wrong.
     */
    CreateTokenResult createToken(String username, String password, Duration expireAfter) throws UserNotExistException, BadCredentialException;

    /**
     * Validate a token. Throw {@link BadTokenException} if token is invalid. Return the user info of the token.
     *
     * @param token The token to validate.
     * @return The info of the user of the token.
     * @throws BadTokenException     Thrown when token is invalid.
     * @throws TokenExpiredException Thrown when token is expired.
     */
    UserInfo verifyToken(String token) throws BadTokenException, TokenExpiredException;

    /**
     * Get the owner user's id of the token. If token does not exist, return empty optional.
     * @param token The token.
     * @return The user id. Or an empty Optional if token does not exist.
     */
    Optional<Long> getTokenUserId(String token);

    /**
     * Revoke a token, which means it is not valid any more.
     *
     * @param token The token to revoke.
     */
    void revokeToken(String token);
}
