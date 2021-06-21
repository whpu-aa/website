package whpuaa.website.controller.model;

public class HttpVerifyTokenRequest {
    private String token;

    public HttpVerifyTokenRequest() {
    }

    public HttpVerifyTokenRequest(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
