package whpuaa.website.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.logout.LogoutFilter;

@Configuration
public class WebsiteWebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    TokenAuthenticationProcessingFilter tokenAuthenticationProcessingFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterAfter(tokenAuthenticationProcessingFilter, LogoutFilter.class)
                .csrf().disable()
                .authorizeRequests(authorize ->
                        authorize
                                .mvcMatchers("/api/token/create", "/api/token/verify").permitAll()
                                .mvcMatchers("/api/token/revoke").authenticated());
    }
}
