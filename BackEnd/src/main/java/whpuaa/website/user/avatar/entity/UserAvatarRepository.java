package whpuaa.website.user.avatar.entity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAvatarRepository extends CrudRepository<UserAvatar, Long> {

}
