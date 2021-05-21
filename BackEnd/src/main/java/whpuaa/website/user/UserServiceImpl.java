package whpuaa.website.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserServiceImpl implements UserService {

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

    @Override
    public User createUser(String username, String password) {
        User user = createUserEntity(username, password);
        userRepository.save(user);
        return user;
    }
}
