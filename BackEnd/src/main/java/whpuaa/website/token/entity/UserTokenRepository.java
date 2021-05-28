package whpuaa.website.token.entity;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserTokenRepository extends CrudRepository<UserToken, Long> {
    Optional<UserToken> findByToken(String token);
    void deleteByToken(String token);
}
