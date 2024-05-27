package numerario.auth.api.auth.modules.user.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import numerario.auth.api.auth.modules.user.dto.CreateUserRequestDto;
import numerario.auth.api.auth.modules.user.dto.UserProfileResponseDto;
import numerario.auth.api.auth.modules.user.use_cases.CreateUserUseCase;
import numerario.auth.api.auth.modules.user.use_cases.UserProfileUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController()
@RequestMapping("/user")
@Tag(name = "/user", description = "Autenticacao usuario")
public class UserController {

    private final CreateUserUseCase createUserUseCase;
    private final UserProfileUseCase userProfileUseCase;

    public UserController(CreateUserUseCase createUserUseCase, UserProfileUseCase userProfileUseCase) {
        this.createUserUseCase = createUserUseCase;
        this.userProfileUseCase = userProfileUseCase;
    }

    @PostMapping()
    @Operation(
            summary = "Criaçào de usuarios",
            description = "Criacao de usuario"
    )
    @ApiResponse(
            responseCode = "201",
            content = {
                    @Content(
                            array = @ArraySchema(schema = @Schema(implementation = UUID.class)))
            })
    public ResponseEntity<UUID> create(@Valid @RequestBody() CreateUserRequestDto user) {
        var createdUserId = this.createUserUseCase.execute(user);
        return ResponseEntity.created(URI.create("/")).body(createdUserId);
    }

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<UserProfileResponseDto> get(HttpServletRequest request) {
        var userId = request.getAttribute("user_id");
        var user = this.userProfileUseCase.execute(UUID.fromString(userId.toString()));

        return ResponseEntity.ok().body(user);
    }
}
