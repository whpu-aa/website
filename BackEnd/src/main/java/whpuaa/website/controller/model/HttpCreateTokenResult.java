package whpuaa.website.controller.model;

public class HttpCreateTokenResult {
    private String token;
    private HttpUserInfo user;

    public HttpCreateTokenResult(String token, HttpUserInfo user) {
        this.token = token;
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public HttpUserInfo getUser() {
        return user;
    }

    public void setUser(HttpUserInfo user) {
        this.user = user;
    }
}
