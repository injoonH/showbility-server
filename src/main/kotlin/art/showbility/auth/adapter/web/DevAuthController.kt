package art.showbility.auth.adapter.web

import art.showbility.auth.app.service.JwtProvider
import art.showbility.common.ActiveProfile
import art.showbility.common.DEV_PUBLIC_PREFIX
import art.showbility.user.domain.UserId
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.context.annotation.Profile
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@Profile(ActiveProfile.NOT_LIVE)
@RestController
@Tag(name = "Auth")
class DevAuthController(
    private val jwtProvider: JwtProvider,
) {
    @GetMapping("$DEV_PUBLIC_PREFIX/auth/tokens/{userId}")
    @Operation(
        summary = "Issue auth token for a user",
    )
    fun issueToken(
        @PathVariable userId: UserId,
    ): ResponseEntity<String> {
        val token = jwtProvider.generateToken(userId, 3_600)
        return ResponseEntity.ok(token)
    }
}
