package whpuaa.website.user;

import java.util.List;
import java.util.Map;

public class UserModifyParams {
    private String username;
    private String name;
    private String password;
    private String description;
    private List<String> permission;
    private Map<String, String> details;

    public UserModifyParams() {

    }

    public UserModifyParams(String username, String name, String password, String description, List<String> permission, Map<String, String> details) {
        this.username = username;
        this.name = name;
        this.password = password;
        this.description = description;
        this.permission = permission;
        this.details = details;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getPermission() {
        return permission;
    }

    public void setPermission(List<String> permission) {
        this.permission = permission;
    }

    public Map<String, String> getDetails() {
        return details;
    }

    public void setDetails(Map<String, String> details) {
        this.details = details;
    }
}
