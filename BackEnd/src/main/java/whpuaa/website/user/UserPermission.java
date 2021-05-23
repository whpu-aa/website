package whpuaa.website.user;

import javax.persistence.*;

@Entity(name = "user_permission")
public class UserPermission {
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String permission;

    @ManyToOne(optional = false)
    @JoinColumn(name="user_id", foreignKey = @ForeignKey(name = "FK_USER_PERMISSION_USER_ID"))
    private User user;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserPermission{" +
                "id=" + id +
                ", permission='" + permission + '\'' +
                ", user=" + user +
                '}';
    }
}
