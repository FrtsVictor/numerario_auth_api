package numerario.auth.api.auth.modules.role;

import jakarta.persistence.*;
import lombok.Data;
import numerario.auth.api.auth.modules.user.UserEntity;
import org.hibernate.validator.constraints.Length;

import java.util.HashSet;
import java.util.List;
import java.util.UUID;

@Data
@Entity(name = "roles")
public class RoleEntity {

    @Id()
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Length(min = 4, max = 50)
    @Column(length = 50)
    private String name;

    @ManyToMany(mappedBy = "roles")
    List<UserEntity> users;
}
