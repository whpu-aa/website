package whpuaa.website.controller.model;

public class HttpVerifyTokenResult {
    private HttpUserInfo user;

    public HttpVerifyTokenResult() {
    }

    public HttpVerifyTokenResult(HttpUserInfo user) {
        this.user = user;
    }

    public HttpUserInfo getUser() {
        return user;
    }

    public void setUser(HttpUserInfo user) {
        this.user = user;
    }
}
