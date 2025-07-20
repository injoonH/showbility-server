package art.showbility.user.app.port.outbound

import art.showbility.user.domain.UserId

interface CreateUserPort {
    fun create(command: CreateUserCommand): UserId
}

data class CreateUserCommand(
    val handle: String,
    val nickname: String,
)
