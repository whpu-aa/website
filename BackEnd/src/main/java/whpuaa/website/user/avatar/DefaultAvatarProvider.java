package whpuaa.website.user.avatar;

import whpuaa.website.util.ByteData;
import whpuaa.website.util.DataCacheInfo;

public interface DefaultAvatarProvider {
    /**
     * Get default avatar cache info.
     *
     * @return Default avatar cache info.
     */
    DataCacheInfo getDefaultAvatarCacheInfo();

    /**
     * Get the default avatar.
     *
     * @return The default avatar data.
     */
    ByteData getDefaultAvatarData();
}
