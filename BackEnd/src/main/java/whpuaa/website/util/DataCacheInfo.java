package whpuaa.website.util;

import java.util.Date;

public class DataCacheInfo {
    private Date lastUpdate;
    private String hash;

    public DataCacheInfo(Date lastUpdate, String hash) {
        this.lastUpdate = lastUpdate;
        this.hash = hash;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
}
