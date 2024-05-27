package numerario.auth.api.auth.modules.user.use_cases;

import numerario.auth.api.auth.modules.role.repositories.RoleRepository;
import numerario.auth.api.auth.modules.user.dto.GrantUsersRolesRequestDto;
import numerario.auth.api.auth.modules.user.repositories.UserRepository;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GrantUsersRolesUseCase {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public GrantUsersRolesUseCase(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public void execute(List<GrantUsersRolesRequestDto> users) {
        users.forEach(u -> {
            var user = this.userRepository.findById(u.getUserId()).orElseThrow();
            var roles = this.roleRepository.findByIdIn(u.getRoleIds());

            if (roles.size() != u.getRoleIds().size()) {
                try {
                    throw new BadRequestException("Invalid role id");
                } catch (BadRequestException e) {
                    throw new RuntimeException(e);
                }
            }
            user.setActive(u.getActive());
            user.setRoles(roles);
            this.userRepository.save(user);
        });

    }

}
