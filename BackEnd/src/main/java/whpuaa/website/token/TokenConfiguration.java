package whpuaa.website.token;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TokenConfiguration {
    @Bean
    public TokenService tokenService() {
        return new TokenServiceImpl();
    }
}
