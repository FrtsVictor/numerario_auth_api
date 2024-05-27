package numerario.auth.api.auth.modules.user.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class GrantUsersRolesRequestDto {
    @NotNull
    private UUID userId;
    @NotEmpty
    private List<UUID> roleIds;
    @NotNull
    private Boolean active;
}
