package whpuaa.website.controller.model;

import org.springframework.lang.NonNull;

public class HttpVerifyTokenRequest {
    @NonNull
    private String token = "";

    public HttpVerifyTokenRequest() {
    }

    @NonNull
    public String getToken() {
        return token;
    }

    public void setToken(@NonNull String token) {
        this.token = token;
    }
}
