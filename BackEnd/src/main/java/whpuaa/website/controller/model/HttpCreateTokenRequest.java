package whpuaa.website.controller.model;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

public class HttpCreateTokenRequest {
    @NonNull
    private String username = "";

    @NonNull
    private String password = "";

    @Nullable
    private Double expireAfter;

    public HttpCreateTokenRequest() {
    }

    public HttpCreateTokenRequest(@NonNull String username, @NonNull String password, @Nullable Double expireAfter) {
        this.username = username;
        this.password = password;
        this.expireAfter = expireAfter;
    }

    @NonNull
    public String getUsername() {
        return username;
    }

    public void setUsername(@NonNull String username) {
        this.username = username;
    }

    @NonNull
    public String getPassword() {
        return password;
    }

    public void setPassword(@NonNull String password) {
        this.password = password;
    }

    @Nullable
    public Double getExpireAfter() {
        return expireAfter;
    }

    public void setExpireAfter(@Nullable Double expireAfter) {
        this.expireAfter = expireAfter;
    }
}
