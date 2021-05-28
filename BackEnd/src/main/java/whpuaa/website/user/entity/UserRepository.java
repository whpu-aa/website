package whpuaa.website.user.entity;

import org.springframework.data.repository.CrudRepository;
import whpuaa.website.user.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {
    boolean existsByUsername(String username);
}
