package art.showbility.user.adapter.web

import art.showbility.auth.domain.AuthPrincipal
import art.showbility.auth.domain.AuthSecurityScheme
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@Tag(name = "User")
class UserController {
    @GetMapping("/users/me")
    @Operation(
        summary = "Get authenticated user information",
        security = [SecurityRequirement(name = AuthSecurityScheme.AUTH_TOKEN)],
    )
    fun getMyself(
        @AuthenticationPrincipal principal: AuthPrincipal,
    ): ResponseEntity<ULong> {
        val userId = principal.userId.value
        return ResponseEntity.ok(userId)
    }
}
