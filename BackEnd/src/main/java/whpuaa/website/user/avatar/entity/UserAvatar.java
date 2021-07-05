package whpuaa.website.user.avatar.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity(name = "user_avatar")
public class UserAvatar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "user_id", nullable = false)
    private long userId;

    @Column(name = "data")
    private byte[] data;

    @Column(name = "content_type")
    private String contentType;

    @Column(name = "hash")
    private String hash;

    @Column(name = "last_update")
    private Timestamp lastUpdate;

    public UserAvatar() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
