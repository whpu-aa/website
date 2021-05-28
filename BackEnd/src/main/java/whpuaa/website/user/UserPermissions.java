package whpuaa.website.user;

import java.util.HashSet;
import java.util.Set;

public class UserPermissions {
    private UserPermissions() {

    }

    public static final String USER_MANAGEMENT = "UserManagement";
    public static final String NEWS_MANAGEMENT = "NewsManagement";
    public static final Set<String> PERMISSION_SET = new HashSet<>();

    static {
        PERMISSION_SET.add(USER_MANAGEMENT);
        PERMISSION_SET.add(NEWS_MANAGEMENT);
    }
}
