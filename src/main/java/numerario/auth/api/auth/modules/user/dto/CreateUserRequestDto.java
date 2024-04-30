package numerario.auth.api.auth.modules.user.dto;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequestDto {
    @Length(min = 4, max = 30)
    private String username;

    @Email
    private String email;

    @Length(min = 8, max = 20)
    private String password;

    @Length(min = 4, max = 80)
    private String name;
}
