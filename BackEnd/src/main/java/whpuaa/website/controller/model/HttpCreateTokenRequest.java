package whpuaa.website.controller.model;

public class HttpCreateTokenRequest {
    private String username;
    private String password;
    private double expireAfter;

    public HttpCreateTokenRequest() {
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

    public double getExpireAfter() {
        return expireAfter;
    }

    public void setExpireAfter(double expireAfter) {
        this.expireAfter = expireAfter;
    }
}
