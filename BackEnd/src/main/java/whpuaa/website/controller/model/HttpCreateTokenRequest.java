package whpuaa.website.controller.model;

public class HttpCreateTokenRequest {
    private String username;

    private String password;

    private Double expireAfter;

    public HttpCreateTokenRequest() {
    }

    public HttpCreateTokenRequest(String username, String password, Double expireAfter) {
        this.username = username;
        this.password = password;
        this.expireAfter = expireAfter;
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

    public Double getExpireAfter() {
        return expireAfter;
    }

    public void setExpireAfter(Double expireAfter) {
        this.expireAfter = expireAfter;
    }
}
