package whpuaa.website.user.avatar;

import whpuaa.website.user.UserNotExistException;
import whpuaa.website.util.ByteData;
import whpuaa.website.util.DataCacheInfo;
import whpuaa.website.util.image.ImageException;

public interface UserAvatarService {
    /**
     * Get the cache info of a user's avatar.
     *
     * @param userId User's id.
     * @return The cache data of the avatar.
     * @throws UserNotExistException Thrown when user does not exist.
     */
    DataCacheInfo getAvatarCacheInfo(long userId) throws UserNotExistException;

    /**
     * Get the avatar data of the user.
     *
     * @param userId User's id.
     * @return The data of the avatar.
     * @throws UserNotExistException Thrown when user does not exist.
     */
    ByteData getAvatarData(long userId) throws UserNotExistException;

    /**
     * Set avatar of a user.
     *
     * @param userId The id of the user.
     * @param data   The avatar of the user.
     * @return Avatar cache info.
     * @throws UserNotExistException Thrown when user does not exist.
     * @throws ImageException        Thrown when image is illegal.
     */
    DataCacheInfo setAvatar(long userId, ByteData data) throws UserNotExistException, ImageException;

    /**
     * Delete avatar of a user.
     *
     * @param userId The id of the user.
     * @throws UserNotExistException Thrown when user does not exist.
     */
    void deleteAvatar(long userId) throws UserNotExistException;
}
