package whpuaa.website.controller.model;

public class HttpRevokeTokenRequest {
    private String token;

    public HttpRevokeTokenRequest() {
    }

    public HttpRevokeTokenRequest(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
