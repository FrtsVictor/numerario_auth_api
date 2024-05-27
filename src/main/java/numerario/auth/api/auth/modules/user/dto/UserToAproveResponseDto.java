package numerario.auth.api.auth.modules.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserToAproveResponseDto {
    private String email;
    private UUID id;
    private String name;
    private Set<String> roles;
}
