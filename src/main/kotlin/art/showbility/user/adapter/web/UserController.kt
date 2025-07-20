package art.showbility.user.adapter.web

import art.showbility.auth.domain.AuthPrincipal
import art.showbility.auth.domain.AuthSecurityScheme
import art.showbility.user.app.port.inbound.UpdateUserUseCase
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
@Tag(name = "User")
class UserController(
    private val updateUserUseCase: UpdateUserUseCase,
) {
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

    @PutMapping("/users/me")
    @Operation(
        summary = "Update authenticated user information",
        responses = [ApiResponse(responseCode = "204")],
        security = [SecurityRequirement(name = AuthSecurityScheme.AUTH_TOKEN)],
    )
    fun updateMyself(
        @AuthenticationPrincipal principal: AuthPrincipal,
        @Valid @RequestBody request: UserRequest.UpdateMyselfRequest,
    ): ResponseEntity<Unit> {
        updateUserUseCase.updateUser(
            id = principal.userId,
            nickname = request.nickname,
        )
        return ResponseEntity.noContent().build()
    }
}
