package numerario.auth.api.auth.modules.user.repositories;

import numerario.auth.api.auth.modules.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    Optional<UserEntity> findByUsernameOrEmail(String username, String password);

    Optional<UserEntity> findByUsername(String username);
}
