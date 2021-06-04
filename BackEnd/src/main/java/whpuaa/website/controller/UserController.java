package whpuaa.website.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import whpuaa.website.auth.TokenAuthentication;
import whpuaa.website.controller.model.*;
import whpuaa.website.user.*;
import whpuaa.website.util.ListWithTotalCount;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UsernameValidator usernameValidator;

    @Autowired
    private PasswordValidator passwordValidator;

    private HttpUserInfo mapUserInfoToHttp(UserInfo user) {
        // TODO: Fill in avatarUrl when implemented.
        return new HttpUserInfo(user, null);
    }

    private void validateUserPermission(List<String> permissions, boolean allowNull) {
        if (allowNull && permissions == null) return;
        if (!allowNull && permissions == null) throw new InvalidModelException("Permission can't be null.");
        if (!UserPermissions.PERMISSION_SET.containsAll(permissions))
            throw new InvalidModelException("Permission is invalid.");
    }

    @GetMapping("/users")
    public ResponseEntity<HttpItemsWithTotalCount<HttpUserInfo>> list(@RequestParam(value = "page", required = false) Long page,
                                                                      @RequestParam(value = "numberPerPage", required = false) Long numberPerPage) {
        if (page != null && page < 0) throw new InvalidModelException("Page can't be smaller than 0.");
        if (numberPerPage != null && numberPerPage <= 0)
            throw new InvalidModelException("Number per page must be greater than 0.");

        ListWithTotalCount<UserInfo> list = userService.getUsers(page == null ? 0 : page, numberPerPage == null ? 20 : numberPerPage);

        return ResponseEntity.ok(new HttpItemsWithTotalCount<>(list, this::mapUserInfoToHttp));
    }

    @GetMapping("/users/{user_id}")
    public ResponseEntity<HttpUserInfo> get(@PathVariable("user_id") long userId) throws UserNotExistException {
        UserInfo user = userService.getUser(userId);
        return ResponseEntity.ok(mapUserInfoToHttp(user));
    }

    @PostMapping("/users")
    public ResponseEntity<HttpUserInfo> post(@RequestBody HttpPostUserRequest body) throws UsernameConflictException, InvalidOperationOnRootUserException, UserNotExistException {
        usernameValidator.validateAndDoIfFailed(body.getUsername(), false, (m) -> {
            throw new InvalidModelException(m);
        });
        passwordValidator.validateAndDoIfFailed(body.getPassword(), false, (m) -> {
            throw new InvalidModelException(m);
        });
        validateUserPermission(body.getPermission(), true);

        UserInfo u = userService.createUser(body.getUsername(), body.getPassword());
        UserInfo user = userService.modifyUser(u.getId(), new UserModifyParams(null, body.getName(), null, body.getDescription(), body.getPermission(), body.getDetails()));
        return ResponseEntity.ok(mapUserInfoToHttp(user));
    }

    @PatchMapping("/users/{user_id}")
    public ResponseEntity<HttpUserInfo> patch(@PathVariable("user_id") long userId, @RequestBody HttpPatchUserRequest body, TokenAuthentication authentication) throws InvalidOperationOnRootUserException, UserNotExistException, UsernameConflictException {
        if (!authentication.getUser().getPermission().contains(UserPermissions.USER_MANAGEMENT) && (body.getUsername() != null || body.getPassword() != null || body.getName() != null || body.getPermission() != null)) {
            return ResponseEntity.status(403).build();
        }
        usernameValidator.validateAndDoIfFailed(body.getUsername(), true, (m) -> {
            throw new InvalidModelException(m);
        });
        passwordValidator.validateAndDoIfFailed(body.getPassword(), true, (m) -> {
            throw new InvalidModelException(m);
        });
        validateUserPermission(body.getPermission(), true);

        UserInfo user = userService.modifyUser(userId, new UserModifyParams(body.getUsername(), body.getName(), body.getPassword(), body.getDescription(), body.getPermission(), body.getDetails()));

        return ResponseEntity.ok(mapUserInfoToHttp(user));
    }

    @DeleteMapping("/users/{user_id}")
    public void patch(@PathVariable("user_id") long userId) throws InvalidOperationOnRootUserException {
        userService.removeUser(userId);
    }

    @ExceptionHandler
    public ResponseEntity<HttpCommonErrorResponse> handleUserNotExistException(UserNotExistException e, HttpServletRequest request) {
        if (request.getMethod().equals("GET"))
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.badRequest().body(new HttpCommonErrorResponse(100201, "A user with given username already exists."));
    }

    @ExceptionHandler
    public ResponseEntity<HttpCommonErrorResponse> handleUsernameConflictException(UsernameConflictException e) {
        return ResponseEntity.badRequest().body(new HttpCommonErrorResponse(100201, "A user with given username already exists."));
    }

    @ExceptionHandler
    public ResponseEntity<HttpCommonErrorResponse> handleInvalidOperationOnRootUserException(InvalidOperationOnRootUserException e) {
        return ResponseEntity.badRequest().body(new HttpCommonErrorResponse(100203, e.getMessage()));
    }
}
