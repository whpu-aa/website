package whpuaa.website;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import whpuaa.website.user.UserRepository;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class DatabaseTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void rootUserShouldCreate() {
        assertThat(userRepository.count()).isEqualTo(1);
        assertThat(userRepository.findById(1L)).hasValueSatisfying(u -> {
            assertThat(u.getUsername()).isEqualTo("root");
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            assertThat(passwordEncoder.matches("rootroot", u.getPassword())).isTrue();
        });
    }
}
