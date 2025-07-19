package art.showbility.user.adapter.web

import art.showbility.common.ActiveProfile
import art.showbility.common.DEV_PUBLIC_PREFIX
import art.showbility.user.app.port.inbound.RegisterUserUseCase
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.context.annotation.Profile
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@Profile(ActiveProfile.NOT_LIVE)
@RestController
@Tag(name = "User")
class DevUserController(
    private val registerUserUseCase: RegisterUserUseCase,
) {
    @PostMapping("$DEV_PUBLIC_PREFIX/users")
    @Operation(
        summary = "Register a test user",
        responses = [ApiResponse(responseCode = "201")],
    )
    fun registerTestUser(
        @RequestBody request: UserRequest.RegisterTestUserRequest,
    ): ResponseEntity<ULong> {
        val userId =
            registerUserUseCase.register(
                handle = request.handle,
                nickname = request.nickname,
            )
        return ResponseEntity.created(URI("/users/${userId.value}")).body(userId.value)
    }
}
