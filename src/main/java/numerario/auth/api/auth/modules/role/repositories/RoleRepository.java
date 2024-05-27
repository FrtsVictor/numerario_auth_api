package numerario.auth.api.auth.modules.role.repositories;

import numerario.auth.api.auth.modules.role.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, UUID> {

    List<RoleEntity> findByIdIn(List<UUID> ids);
}
