package numerario.auth.api.auth.modules.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthUserConfirmationTokenRequestDto {
    private String email;
    private String accessKey;
}
