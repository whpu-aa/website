package whpuaa.website.controller.token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import whpuaa.website.controller.InvalidModelException;
import whpuaa.website.controller.model.*;
import whpuaa.website.token.BadTokenException;
import whpuaa.website.token.TokenExpiredException;
import whpuaa.website.token.TokenService;
import whpuaa.website.user.*;

import java.time.Duration;
import java.util.Optional;

@RestController
@RequestMapping("/api/token")
public class TokenController {

    @Autowired
    private UsernameValidator usernameValidator;

    @Autowired
    private PasswordValidator passwordValidator;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/create")
    public ResponseEntity<HttpCreateTokenResult> create(@RequestBody HttpCreateTokenRequest body) throws BadCredentialException {
        usernameValidator.validateAndDoIfFailed(body.getUsername(), false, (message) -> {
            throw new InvalidModelException(message);
        });

        passwordValidator.validateAndDoIfFailed(body.getPassword(), false, (message) -> {
            throw new InvalidModelException(message);
        });

        Double expireAfter = body.getExpireAfter();
        Duration expireAfterDuration;
        if (expireAfter != null) {
            if (expireAfter <= 0)
                throw new InvalidModelException("Expire after must be greater than 0.");
            if (expireAfter > 365)
                throw new InvalidModelException("Expire after can't be bigger than 365.");
            expireAfterDuration = Duration.ofSeconds((long) expireAfter.doubleValue() * 24 * 3600);
        } else {
            expireAfterDuration = Duration.ofDays(1);
        }

        // why catch here? Because I need to convert exception and hide whether username or password is wrong in message.
        try {
            TokenService.CreateTokenResult result = tokenService.createToken(body.getUsername(), body.getPassword(), expireAfterDuration);

            // TODO: Fill in avatar url when implemented.
            return ResponseEntity.ok(new HttpCreateTokenResult(result.token, new HttpUserInfo(result.user, null)));
        } catch (BadCredentialException e) {
            throw new BadCredentialException("Username or password is wrong.");
        } catch (UserNotExistException e) {
            throw new BadCredentialException("Username or password is wrong,");
        }
    }

    @PostMapping("/verify")
    public ResponseEntity<HttpVerifyTokenResult> verify(@RequestBody HttpVerifyTokenRequest body) throws BadTokenException, TokenExpiredException {
        UserInfo user = tokenService.verifyToken(body.getToken());
        // TODO: Fill in avatar url when implemented.
        return ResponseEntity.ok(new HttpVerifyTokenResult(new HttpUserInfo(user, null)));
    }

    @PostMapping("/revoke")
    public void revoke(@RequestBody HttpRevokeTokenRequest body, Authentication authentication) {
        Optional<Long> optionalUserId = tokenService.getTokenUserId(body.getToken());
        if (optionalUserId.isEmpty()) return;
        // shu... Don't tell him/her it's other's token!!! Just return peacefully!
        if (optionalUserId.get() != ((UserInfo) authentication.getPrincipal()).getId()) return;
        tokenService.revokeToken(body.getToken());
    }

    @ExceptionHandler
    public ResponseEntity<HttpCommonErrorResponse> handleBadCredentialException(BadCredentialException e) {
        return ResponseEntity.badRequest().body(new HttpCommonErrorResponse(100101, e.getMessage()));
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<HttpCommonErrorResponse> handleBadTokenException(BadTokenException e) {
        return ResponseEntity.badRequest().body(new HttpCommonErrorResponse(100102, e.getMessage()));
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<HttpCommonErrorResponse> handleTokenExpiredException(TokenExpiredException e) {
        return ResponseEntity.badRequest().body(new HttpCommonErrorResponse(100103, e.getMessage()));
    }
}
