package whpuaa.website.controller.model;

import whpuaa.website.user.UserInfo;

import java.util.List;
import java.util.Map;

public class HttpUserInfo extends UserInfo {

    private String avatarUrl;

    public HttpUserInfo() {

    }

    public HttpUserInfo(UserInfo base, String avatarUrl) {
        super(base);
        this.avatarUrl = avatarUrl;
    }

    public HttpUserInfo(long id, String username, String name, String description, List<String> permission, Map<String, String> details, String avatarUrl) {
        super(id, username, name, description, permission, details);
        this.avatarUrl = avatarUrl;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}
