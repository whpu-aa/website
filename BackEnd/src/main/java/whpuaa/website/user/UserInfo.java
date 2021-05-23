package whpuaa.website.user;

import java.util.List;
import java.util.Map;

public class UserInfo {
    private long id;
    private String username;
    private String name;
    private String description;
    private List<String> permission;
    private Map<String, String> details;
    public UserInfo(){}
    public UserInfo(long id, String username, String name, String description, List<String> permission, Map<String, String> details) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.description = description;
        this.permission = permission;
        this.details = details;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "UserInfo{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", permission=" + permission +
                ", details=" + details +
                '}';
    }
}
