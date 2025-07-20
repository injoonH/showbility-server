package art.showbility.user.app.port.inbound

import art.showbility.user.domain.UserId

interface RegisterUserUseCase {
    fun register(
        handle: String,
        nickname: String,
    ): UserId
}
