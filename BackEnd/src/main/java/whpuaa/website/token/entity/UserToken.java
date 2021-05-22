package whpuaa.website.token.entity;

import whpuaa.website.user.User;

import javax.persistence.*;

@Entity(name = "user_token")
public class UserToken {
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String token;

    @ManyToOne(optional = true)
    @JoinColumn(name="user_id", foreignKey = @ForeignKey(name = "FK_USER_TOKEN_USER_ID"))
    private User user;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
