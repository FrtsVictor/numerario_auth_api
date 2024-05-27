package numerario.auth.api.auth.modules.user.use_cases;

import numerario.auth.api.auth.modules.role.RoleEntity;
import numerario.auth.api.auth.modules.user.dto.UserToAproveResponseDto;
import numerario.auth.api.auth.modules.user.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service
public class GetUsersToAproveUseCase {

    private final UserRepository userRepository;

    public GetUsersToAproveUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Stream<UserToAproveResponseDto> execute() {
        var users = this.userRepository.findByActive(false);

        return users.stream().map(it -> UserToAproveResponseDto.builder()
                .id(it.getId())
                .name(it.getName())
                .email(it.getEmail()).roles(it.getRoles().stream().map(RoleEntity::getName).collect(Collectors.toSet()))
                .build()
        );
    }
}
