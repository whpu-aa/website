package whpuaa.website.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import whpuaa.website.user.entity.User;
import whpuaa.website.user.entity.UserDetail;
import whpuaa.website.user.entity.UserPermission;
import whpuaa.website.user.entity.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {

    @Autowired
    private UsernameValidator usernameValidator;

    @Autowired
    private PasswordValidator passwordValidator;

    @Autowired
    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    private User createUserEntity(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setName("");
        user.setDescription("");
        return user;
    }

    private UserInfo mapEntityToInfo(User user) {
        return new UserInfo(
                user.getId(),
                user.getUsername(),
                user.getName() != null ? user.getName() : "",
                user.getDescription() != null ? user.getDescription() : "",
                user.getPermissions().stream().map(UserPermission::getPermission).collect(Collectors.toList()),
                user.getDetails().stream().collect(Collectors.toMap(UserDetail::getKey, UserDetail::getValue)));
    }

    @Override
    public long getUserId(String username) throws UserNotExistException {
        // TODO(Liu De): Implement this!
        return 0;
    }

    @Override
    public UserInfo getUser(long id) throws UserNotExistException {
        // TODO(Liu De): Implement this!
        return null;
    }

    @Override
    public UserInfo getUserByUsername(String username) throws UserNotExistException {
        // TODO(Liu De): Implement this!
        return null;
    }

    @Override
    public List<UserInfo> getUsers(long page, long numberPerPage) {
        // TODO(Liu De): Implement this!
        return null;
    }

    @Override
    public UserInfo createUser(@NonNull String username, @NonNull String password) throws UsernameConflictException {
        usernameValidator.ValidateAndThrow(username, false);
        passwordValidator.Validate(password, false);

        if (userRepository.existsByUsername(username))
            throw new UsernameConflictException("Failed to create user because this username already exists.");

        User user = createUserEntity(username, password);
        userRepository.save(user);
        return mapEntityToInfo(user);
    }

    @Override
    public UserInfo modifyUser(long id, UserModifyParams params) throws UsernameConflictException, InvalidOperationOnRootUserException {
        // TODO(Liu De): Implement this!
        return null;
    }

    @Override
    public boolean removeUser(long id) throws InvalidOperationOnRootUserException {
        // TODO(Liu De): Implement this!
        return false;
    }

    @Override
    public UserInfo verifyUserCredential(String username, String password) throws BadCredentialException {
        // TODO(Liu De): Implement this!
        return null;
    }
}
