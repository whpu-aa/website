package whpuaa.website.controller.model;

import org.springframework.lang.NonNull;

public class HttpRevokeTokenRequest {
    @NonNull
    private String token = "";

    public HttpRevokeTokenRequest() {
    }

    @NonNull
    public String getToken() {
        return token;
    }

    public void setToken(@NonNull String token) {
        this.token = token;
    }
}
