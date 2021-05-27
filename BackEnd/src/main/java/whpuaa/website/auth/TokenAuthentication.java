package whpuaa.website.auth;

import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import whpuaa.website.user.UserInfo;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class TokenAuthentication implements Authentication {

    private boolean authenticated = false;

    private String token = null;
    private UserInfo user = null;
    private List<PermissionGrantedAuthority> authorities;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserInfo getUser() {
        return user;
    }

    public void setUser(@NonNull UserInfo user) {
        this.user = user;
        authorities = user.getPermission().stream().map(PermissionGrantedAuthority::new).collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public Object getCredentials() {
        return token;
    }

    @Override
    public Object getDetails() {
        return user.getDetails();
    }

    @Override
    public Object getPrincipal() {
        return user;
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        authenticated = isAuthenticated;
    }

    @Override
    public String getName() {
        return user == null ? null : user.getUsername();
    }
}
