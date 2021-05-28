package whpuaa.website.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.lang.NonNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import whpuaa.website.user.entity.User;
import whpuaa.website.user.entity.UserDetail;
import whpuaa.website.user.entity.UserPermission;
import whpuaa.website.user.entity.UserRepository;
import whpuaa.website.util.ListWithTotalCount;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UsernameValidator usernameValidator;

    @Autowired
    private PasswordValidator passwordValidator;

    @Autowired
    private UserRepository userRepository;

    @Autowired
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
                user.getId() == 1 ? new ArrayList<>(UserPermissions.PERMISSION_SET) : user.getPermissions().stream().map(UserPermission::getPermission).collect(Collectors.toList()),
                user.getDetails().stream().collect(Collectors.toMap(UserDetail::getKey, UserDetail::getValue)));
    }

    private void throwUserNotExistException(String username) throws UserNotExistException {
        throw new UserNotExistException("User with username '" + username + "' does not exist.");
    }

    private void throwUserNotExistException(long id) throws UserNotExistException {
        throw new UserNotExistException("User with id '" + id + "' does not exist.");
    }

    @Override
    public long getUserId(String username) throws UserNotExistException {
        Optional<Long> id = userRepository.findIdByUsername(username);
        if (id.isEmpty()) throwUserNotExistException(username);
        return id.get();
    }

    @Override
    public UserInfo getUser(long id) throws UserNotExistException {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) throwUserNotExistException(id);
        return mapEntityToInfo(user.get());
    }

    @Override
    public UserInfo getUserByUsername(String username) throws UserNotExistException {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()) throwUserNotExistException(username);
        return mapEntityToInfo(user.get());
    }

    @Override
    public ListWithTotalCount<UserInfo> getUsers(long page, long numberPerPage) {
        Page<User> users = userRepository.findAll(PageRequest.of((int) page, (int) numberPerPage));
        List<UserInfo> userInfoList = users.stream().map(this::mapEntityToInfo).collect(Collectors.toList());
        return new ListWithTotalCount<>(users.getTotalElements(), userInfoList);
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
    public UserInfo modifyUser(long id, UserModifyParams params) throws UserNotExistException, UsernameConflictException, InvalidOperationOnRootUserException {
        usernameValidator.ValidateAndThrow(params.getUsername(), true);
        passwordValidator.ValidateAndThrow(params.getPassword(), true);

        List<String> newPermission = params.getPermission();
        if (newPermission != null) {
            if (id == 1)
                throw new InvalidOperationOnRootUserException("You can't change root user's permission.");
            if (!UserPermissions.PERMISSION_SET.containsAll(newPermission))
                throw new IllegalArgumentException("Invalid permission string.");
        }

        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) throwUserNotExistException(id);
        User user = optionalUser.get();

        String newUsername = params.getUsername();
        if (newUsername != null) {
            if (userRepository.existsByUsername(newUsername))
                throw new UsernameConflictException("Failed to modify user's username because the new username already exists.");
            user.setUsername(newUsername);
        }

        String newPassword = params.getPassword();
        if (newPassword != null) {
            user.setPassword(passwordEncoder.encode(newPassword));
        }

        String newName = params.getName();
        if (newName != null) {
            user.setName(newName);
        }

        String newDescription = params.getDescription();
        if (newDescription != null) {
            user.setDescription(newDescription);
        }

        if (newPermission != null) {
            user.setPermissions(newPermission.stream().map(p -> {
                UserPermission entity = new UserPermission();
                entity.setUser(user);
                entity.setPermission(p);
                return entity;
            }).collect(Collectors.toList()));
        }

        Map<String, String> newDetails = params.getDetails();
        if (newDetails != null) {
            for (Map.Entry<String, String> entry : newDetails.entrySet()) {
                if (entry.getValue() == null) {
                    user.getDetails().removeIf(d -> d.getKey().equals(entry.getKey()));
                } else {
                    Optional<UserDetail> detail = user.getDetails().stream().filter(d -> d.getKey().equals(entry.getKey())).findFirst();
                    if (detail.isEmpty()) {
                        UserDetail newDetail = new UserDetail();
                        newDetail.setKey(entry.getKey());
                        newDetail.setValue(entry.getValue());
                        newDetail.setUser(user);
                        user.getDetails().add(newDetail);
                    } else {
                        detail.get().setValue(entry.getValue());
                    }
                }
            }
        }

        userRepository.save(user);

        return mapEntityToInfo(user);
    }

    @Override
    public boolean removeUser(long id) throws InvalidOperationOnRootUserException {
        if (id == 1) throw new InvalidOperationOnRootUserException("You can't delete root user.");

        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public UserInfo verifyUserCredential(String username, String password) throws UserNotExistException, BadCredentialException {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isEmpty()) throwUserNotExistException(username);

        User user = optionalUser.get();

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialException("Password is wrong.");
        }

        return mapEntityToInfo(user);
    }
}
