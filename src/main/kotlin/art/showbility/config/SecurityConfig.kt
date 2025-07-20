package art.showbility.config

import art.showbility.auth.app.service.JwtAuthFilter
import art.showbility.auth.app.service.JwtProvider
import art.showbility.common.DEV_PUBLIC_PREFIX
import art.showbility.common.PUBLIC_PREFIX
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.logout.LogoutFilter

@Configuration
@EnableWebSecurity
class SecurityConfig(
    val jwtProvider: JwtProvider,
) {
    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        val jwtAuthFilter = JwtAuthFilter(jwtProvider)

        return http
            .csrf { it.disable() }
            .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .authorizeHttpRequests { authorize ->
                authorize
                    .requestMatchers(
                        "$PUBLIC_PREFIX/**",
                        "$DEV_PUBLIC_PREFIX/**",
                        "/v3/api-docs/**",
                        "/swagger-ui/**",
                    ).permitAll()
                    .anyRequest()
                    .authenticated()
            }
            // https://docs.spring.io/spring-security/reference/servlet/architecture.html#_adding_a_custom_filter
            .addFilterAfter(jwtAuthFilter, LogoutFilter::class.java)
            .build()
    }
}
