package whpuaa.website.token.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity(name = "user_token")
public class UserToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String token;

    @Column(name = "expire_at", nullable = false)
    private Timestamp expireAt;

    @Column(name = "user_id", nullable = false)
    private long userId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Timestamp getExpireAt() {
        return expireAt;
    }

    public void setExpireAt(Timestamp expireAt) {
        this.expireAt = expireAt;
    }
}
