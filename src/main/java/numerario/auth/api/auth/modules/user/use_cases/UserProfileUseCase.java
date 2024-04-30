package numerario.auth.api.auth.modules.user.use_cases;

import numerario.auth.api.auth.modules.user.dto.UserProfileResponseDto;
import numerario.auth.api.auth.modules.user.repositories.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserProfileUseCase {

    private final UserRepository userRepository;

    public UserProfileUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserProfileResponseDto execute(UUID idUser) {
        var user = this.userRepository.findById(idUser).orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return UserProfileResponseDto.builder()
                .username(user.getUsername())
                .name(user.getName())
                .email(user.getEmail())
                .id(user.getId())
                .build();
    }
}
