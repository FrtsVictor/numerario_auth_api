package numerario.auth.api.auth.modules.user.repositories;

import numerario.auth.api.auth.modules.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    Optional<UserEntity> findByUsernameOrEmail(String username, String password);

    Optional<UserEntity> findByEmailAndActive(String email, Boolean active);

    Optional<UserEntity> findByUsername(String username);

    List<UserEntity> findByActive(Boolean active);
}
