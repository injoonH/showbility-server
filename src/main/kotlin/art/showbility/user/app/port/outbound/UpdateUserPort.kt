package art.showbility.user.app.port.outbound

import art.showbility.user.domain.UserId

interface UpdateUserPort {
    fun updateUser(command: UpdateUserCommand)
}

data class UpdateUserCommand(
    val id: UserId,
    val nickname: String,
)
