package whpuaa.website.user;

import javax.persistence.*;

@Entity(name = "user_detail")
public class UserDetail {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    @JoinColumn(name="user_id", foreignKey = @ForeignKey(name = "FK_USER_DETAIL_USER_ID"))
    private User user;

    private String key;

    private String value;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
