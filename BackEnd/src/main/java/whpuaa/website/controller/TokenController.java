package whpuaa.website.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import whpuaa.website.controller.model.HttpCommonErrorResponse;
import whpuaa.website.controller.model.HttpCreateTokenRequest;
import whpuaa.website.controller.model.HttpCreateTokenResult;
import whpuaa.website.controller.model.HttpUserInfo;
import whpuaa.website.token.TokenService;
import whpuaa.website.user.BadCredentialException;
import whpuaa.website.user.UserNotExistException;

import java.time.Duration;

@RestController
@RequestMapping("/api/token")
public class TokenController {

    @Autowired
    private TokenService tokenService;

    @PostMapping("/create")
    public HttpCreateTokenResult get(@RequestBody HttpCreateTokenRequest body) throws BadCredentialException {
        if (body.getExpireAfter() <= 0)
            throw new InvalidModelException("Expire after must be greater than 0.");
        if (body.getExpireAfter() > 365)
            throw new InvalidModelException("Expire after can't be bigger than 365.");

        // why catch here? Because I need to convert exception and hide whether username or password is wrong in message.
        try {
            TokenService.CreateTokenResult result = tokenService.createToken(body.getUsername(), body.getPassword(), Duration.ofSeconds((long) body.getExpireAfter() * 24 * 3600));

            // TODO: Fill in avatar url when implemented.
            return new HttpCreateTokenResult(result.token, new HttpUserInfo(result.user, null));
        } catch (BadCredentialException e) {
            throw new BadCredentialException("Username or password is wrong.");
        } catch (UserNotExistException e) {
            throw new BadCredentialException("Username or password is wrong,");
        }
    }

    @ExceptionHandler
    public HttpCommonErrorResponse handleBadCredentialException(BadCredentialException e) {
        return new HttpCommonErrorResponse(100101, e.getMessage());
    }
}
