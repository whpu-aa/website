package whpuaa.website.auth;

import org.springframework.security.core.GrantedAuthority;

public class PermissionGrantedAuthority implements GrantedAuthority {

    private final String permission;

    public PermissionGrantedAuthority(String permission) {
        this.permission = permission;
    }

    @Override
    public String getAuthority() {
        return permission;
    }
}
