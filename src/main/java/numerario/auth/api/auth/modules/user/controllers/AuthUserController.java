package numerario.auth.api.auth.modules.user.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import numerario.auth.api.auth.modules.user.dto.AuthUserResponseDto;
import numerario.auth.api.auth.modules.user.dto.AuthUserRequestDto;
import numerario.auth.api.auth.modules.user.use_cases.AuthUserUseCase;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Tag(name = "/user", description = "Autenticacao candidato")
public class AuthUserController {

    private final AuthUserUseCase authUserUseCase;

    public AuthUserController(AuthUserUseCase authUserUseCase) {
        this.authUserUseCase = authUserUseCase;
    }

    @PostMapping("/auth")
    @Operation(
            summary = "Autenticacao de usuarios",
            description = "Esta operação tem como objetivo autenticar um usuario"
    )
    @ApiResponse(
            responseCode = "200",
            content = {
                    @Content(
                            array = @ArraySchema(schema = @Schema(implementation = AuthUserResponseDto.class)))
            })
    public AuthUserResponseDto auth(@Valid @RequestBody AuthUserRequestDto dto) {
        return this.authUserUseCase.execute(dto);
    }
}
