package whpuaa.website.user;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UserConfiguration {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UsernameValidator usernameValidator() {
        return new UsernameValidator();
    }

    @Bean
    public PasswordValidator passwordValidator() {
        return new PasswordValidator();
    }
}
