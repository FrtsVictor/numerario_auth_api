package numerario.auth.api.auth.modules.user.use_cases;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import numerario.auth.api.auth.exceptions.AppAuthenticationException;
import numerario.auth.api.auth.modules.role.RoleEntity;
import numerario.auth.api.auth.modules.user.dto.AuthUserRequestDto;
import numerario.auth.api.auth.modules.user.dto.AuthUserResponseDto;
import numerario.auth.api.auth.modules.user.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;

@Service
public class AuthUserUseCase {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Value("${security.auth.secret}")
    private String secret;

    public AuthUserUseCase(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public AuthUserResponseDto execute(AuthUserRequestDto authUserRequestDto) {
        var user = this.userRepository.findByEmailAndActive(authUserRequestDto.getEmail(), true).orElseThrow(AppAuthenticationException::new);

        if (!this.passwordEncoder.matches(authUserRequestDto.getPassword(), user.getPassword())) {
            throw new AppAuthenticationException();
        }

        var userRoles = user.getRoles().stream().map(RoleEntity::getName).toList();
        var expiresIn = Instant.now().plus(Duration.ofHours(4));

        Algorithm algorithm = Algorithm.HMAC256(this.secret);

        var token = JWT.create()
                .withIssuer("numerario-api")
                .withSubject(user.getId().toString())
                .withExpiresAt(expiresIn)
                .withClaim("roles", userRoles)
                .sign(algorithm);

        return AuthUserResponseDto.builder().accessToken(token).accessTokenExpiresIn(expiresIn).build();
    }
}
