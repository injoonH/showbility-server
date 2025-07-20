package art.showbility.user.app.port.inbound

import art.showbility.user.domain.UserId

interface UpdateUserUseCase {
    fun updateUser(
        id: UserId,
        nickname: String,
    )
}
