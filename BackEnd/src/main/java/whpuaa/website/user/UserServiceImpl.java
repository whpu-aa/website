package whpuaa.website.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {
    @Autowired
     private UserMapper userMapper;

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
       return userMapper.getUserId(username);
    }

    @Override
    public UserInfo getUser(long id) throws UserNotExistException {
        // TODO(Liu De): Implement this!
        User user = userMapper.getUserById(id);
        if(user==null)
            throw new UserNotExistException("Failed to find user because this it is not exist.");
        List<String> permissions = userMapper.getPermissions(id);
        Map<String,String> details=new HashMap<String,String>();
        List<Map<String, String>> map = userMapper.getDetails(id);
        for (Map<String, String> detail : map) {
            for (Map.Entry<String, String> entry : detail.entrySet()) {
                details.put(entry.getKey(),entry.getValue());
            }
        }
        UserInfo userInfo = new UserInfo(user.getId(), user.getUsername(), user.getName(), user.getDescription(), permissions, details);
        return userInfo;
    }

    @Override
    public UserInfo getUserByUsername(String username) throws UserNotExistException {
        // TODO(Liu De): Implement this!
        long root = userMapper.getIdByUsername("root");
        UserInfo user =getUser(root);
        return user;
    }

    @Override
    public List<UserInfo> getUsers(long page, long numberPerPage) throws UserNotExistException {
        // TODO(Liu De): Implement this!
        List<Integer> usersid = userMapper.getUsersId(page, numberPerPage);
        List<UserInfo> userInfos=new ArrayList<UserInfo>();
        for (Integer integer : usersid) {
            UserInfo userInfo = getUser(integer);
            userInfos.add(userInfo);
        }
        return userInfos;
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
    public UserInfo modifyUser(long id, UserModifyParams params) throws UsernameConflictException, InvalidOperationOnRootUserException, UserNotExistException {
        // TODO(Liu De): Implement this!
        UserInfo user = getUser(id);
        return new UserInfo(id,params.getUsername(),params.getPassword(),params.getName(),params.getPermission(),params.getDetails());
    }

    @Override
    public boolean removeUser(long id) throws InvalidOperationOnRootUserException {
        // TODO(Liu De): Implement this!
        Integer ans=userMapper.removeUser(id);
        if(ans>1)
            throw new InvalidOperationOnRootUserException("The same id appears，data error!");
        if(null==ans)
            throw new InvalidOperationOnRootUserException("The id is not exist!");
        return true;
    }

    @Override
    public UserInfo verifyUserCredential(String username, String password) throws BadCredentialException, UserNotExistException {
        // TODO(Liu De): Implement this!
        long id = userMapper.getIdByUsername(username);
        User user=userMapper.getUserById(id);
        if(null==user)
            throw new UserNotExistException("This user is not exist!");
        if(user.getPassword().equals(password))
            return getUser(id);
        else
            throw new BadCredentialException("username and password do not matched！");

    }
}
