package whpuaa.website.controller.token;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class TokenApiWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.mvcMatcher("/api/token/create").anonymous()
                .and().mvcMatcher("/api/token/verify").anonymous()
                .and().authorizeRequests(authorize -> authorize.mvcMatchers("/api/token/revoke").authenticated());
    }
}
