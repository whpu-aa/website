package whpuaa.website.controller.model;

import java.util.List;
import java.util.Map;

public class HttpPostUserRequest {
    private String username;
    private String password;
    private String name;
    private List<String> permission;
    private String description;
    private Map<String, String> details;

    public HttpPostUserRequest() {
    }

    public HttpPostUserRequest(String username, String password, String name, List<String> permission, String description, Map<String, String> details) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.permission = permission;
        this.description = description;
        this.details = details;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getPermission() {
        return permission;
    }

    public void setPermission(List<String> permission) {
        this.permission = permission;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Map<String, String> getDetails() {
        return details;
    }

    public void setDetails(Map<String, String> details) {
        this.details = details;
    }
}
