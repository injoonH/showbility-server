package art.showbility.config

import art.showbility.auth.domain.AUTH_TOKEN_HEADER
import art.showbility.auth.domain.AuthSecurityScheme
import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.security.SecurityScheme
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OpenAPIConfig {
    @Bean
    fun openAPI(): OpenAPI =
        OpenAPI()
            .info(
                Info()
                    .title("Showbility API")
                    .description("API documentation for Showbility")
                    .version("0.1.0"),
            ).components(
                Components()
                    .addSecuritySchemes(
                        AuthSecurityScheme.AUTH_TOKEN,
                        SecurityScheme()
                            .name(AUTH_TOKEN_HEADER)
                            .`in`(SecurityScheme.In.HEADER)
                            .type(SecurityScheme.Type.APIKEY),
                    ),
            )
}
