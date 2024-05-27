package numerario.auth.api.auth.modules.user.use_cases;

import jakarta.transaction.Transactional;
import numerario.auth.api.auth.exceptions.UserFoundException;
import numerario.auth.api.auth.modules.mail.use_cases.SendSimpleEmailUseCase;
import numerario.auth.api.auth.modules.user.UserEntity;
import numerario.auth.api.auth.modules.user.dto.CreateUserRequestDto;
import numerario.auth.api.auth.modules.user.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class CreateUserUseCase {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final SendSimpleEmailUseCase sendSimpleEmailUseCase;


    public CreateUserUseCase(PasswordEncoder passwordEncoder, UserRepository userRepository, SendSimpleEmailUseCase sendSimpleEmailUseCase) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.sendSimpleEmailUseCase = sendSimpleEmailUseCase;
    }

    @Transactional(rollbackOn = Exception.class)
    public UUID execute(CreateUserRequestDto createUserRequestDto) {
        this.userRepository.findByUsernameOrEmail(createUserRequestDto.getUsername(), createUserRequestDto.getEmail()).ifPresent(user -> {
            throw new UserFoundException();
        });

        var password = this.passwordEncoder.encode(createUserRequestDto.getPassword());
        UserEntity userToBeCreated = new UserEntity();
        userToBeCreated.setName(createUserRequestDto.getName());
        userToBeCreated.setUsername(createUserRequestDto.getUsername());
        userToBeCreated.setEmail(createUserRequestDto.getEmail());
        userToBeCreated.setPassword(password);
        userToBeCreated.setActive(false);

        var createdUserId = this.userRepository.save(userToBeCreated).getId();
//        this.sendSimpleEmailUseCase.execute(createUserRequestDto.getEmail(), "Conta criada com sucesso, aguarde um gestor aprovar seu login!");

        return createdUserId;
    }
}
