package whpuaa.website.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import whpuaa.website.controller.model.*;
import whpuaa.website.user.*;
import whpuaa.website.util.ListWithTotalCount;

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
    public ResponseEntity<HttpUserInfo> get(@PathVariable("user_id") long userId) {
        try {
            UserInfo user = userService.getUser(userId);
            return ResponseEntity.ok(mapUserInfoToHttp(user));
        } catch (UserNotExistException e) {
            return ResponseEntity.status(404).build();
        }
    }

    @PostMapping("/users")
    public ResponseEntity<HttpUserInfo> post(@RequestBody HttpPostUserRequest body) throws UsernameConflictException, InvalidOperationOnRootUserException, UserNotExistException {
        usernameValidator.validateAndDoIfFailed(body.getUsername(), false, (m) -> {
            throw new InvalidModelException(m);
        });
        passwordValidator.validateAndDoIfFailed(body.getPassword(), false, (m) -> {
            throw new InvalidModelException(m);
        });
        if (body.getPermission() != null && UserPermissions.PERMISSION_SET.containsAll(body.getPermission())) {
            throw new InvalidModelException("Permission is invalid.");
        }

        UserInfo u = userService.createUser(body.getUsername(), body.getPassword());
        UserInfo user = userService.modifyUser(u.getId(), new UserModifyParams(null, body.getName(), null, body.getDescription(), body.getPermission(), body.getDetails()));
        return ResponseEntity.ok(mapUserInfoToHttp(user));
    }

    @PatchMapping("/users/{user_id}")
    public ResponseEntity<HttpUserInfo> patch(@PathVariable("user_id") long userId, @RequestBody HttpPatchUserRequest body) {
        // TODO: Implement this.
        return null;
    }

    @DeleteMapping("/users/{user_id}")
    public void patch(@PathVariable("user_id") long userId) {
        // TODO: Implement this.
    }

    @ExceptionHandler
    public ResponseEntity<HttpCommonErrorResponse> handleUsernameConflictException(UsernameConflictException e) {
        return ResponseEntity.badRequest().body(new HttpCommonErrorResponse(100201, "A user with given username already exists."));
    }
}
