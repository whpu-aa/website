package whpuaa.website.token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.keygen.KeyGenerators;
import whpuaa.website.token.entity.UserToken;
import whpuaa.website.token.entity.UserTokenRepository;
import whpuaa.website.user.BadCredentialException;
import whpuaa.website.user.UserInfo;
import whpuaa.website.user.UserNotExistException;
import whpuaa.website.user.UserService;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.Instant;
import java.util.Optional;

public class TokenServiceImpl implements TokenService {

    @Autowired
    private UserService userService;

    @Autowired
    private UserTokenRepository tokenRepository;

    @Override
    public CreateTokenResult createToken(String username, String password, Duration expireAfter) throws UserNotExistException, BadCredentialException {
        UserInfo user = userService.verifyUserCredential(username, password);
        String token = generateToken();

        UserToken entity = new UserToken();
        entity.setToken(token);
        entity.setUserId(user.getId());
        entity.setExpireAt(Timestamp.from(Instant.now().plus(expireAfter)));
        tokenRepository.save(entity);

        return new CreateTokenResult(token, user);
    }

    @Override
    public UserInfo verifyToken(String token) throws BadTokenException, TokenExpiredException {
        Optional<UserToken> optionalEntity = tokenRepository.findByToken(token);

        if (optionalEntity.isEmpty()) throw new BadTokenException("This token is invalid.");

        UserToken entity = optionalEntity.get();
        if (entity.getExpireAt().before(Timestamp.from(Instant.now())))
            throw new TokenExpiredException("Token is expired.");

        try {
            return userService.getUser(entity.getUserId());
        } catch (UserNotExistException e) {
            throw new RuntimeException("User id associated with this token does not exist. Usually this is impossible. Database might be wrong.");
        }
    }

    @Override
    public void revokeToken(String token) {
        tokenRepository.deleteByToken(token);
    }

    private String generateToken() {
        return KeyGenerators.string().generateKey();
    }
}
