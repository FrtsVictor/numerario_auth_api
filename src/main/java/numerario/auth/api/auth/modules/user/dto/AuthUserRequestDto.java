package numerario.auth.api.auth.modules.user.dto;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthUserRequestDto {
    @Email
    private String email;
    private String password;
}
