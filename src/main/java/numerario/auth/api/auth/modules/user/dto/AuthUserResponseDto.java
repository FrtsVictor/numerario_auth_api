package numerario.auth.api.auth.modules.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthUserResponseDto {
    private String accessToken;
    private Instant accessTokenExpiresIn;
    private String refreshToken;
    private Instant refreshTokenExpiresIn;
}
