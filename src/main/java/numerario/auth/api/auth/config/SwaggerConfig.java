package numerario.auth.api.auth.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {

        return new OpenAPI()
                .info(new Info().title("Gestao de vagas").description("Api para  gestao de vagas").version("1.0"))
                .schemaRequirement("jwt_auth", securitySchema());
                //.addSecurityItem(new SecurityRequirement().addList("Bearer Authentication"))
                //.components(new Components().addSecuritySchemes("Bearer Authentication", securitySchema()));
    }

    private SecurityScheme securitySchema() {
        return new SecurityScheme()
                .name("jwt_auth")
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT");
    }
}
