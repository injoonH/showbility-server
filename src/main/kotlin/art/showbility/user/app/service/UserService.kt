package art.showbility.user.app.service

import art.showbility.user.app.port.inbound.QueryUserUseCase
import art.showbility.user.app.port.inbound.RegisterUserUseCase
import art.showbility.user.app.port.outbound.CreateUserCommand
import art.showbility.user.app.port.outbound.CreateUserPort
import art.showbility.user.app.port.outbound.QueryUserPort
import art.showbility.user.domain.User
import art.showbility.user.domain.UserId
import org.jetbrains.exposed.v1.jdbc.transactions.transaction
import org.springframework.stereotype.Service

@Service
class UserService(
    private val queryUserPort: QueryUserPort,
    private val createUserPort: CreateUserPort,
) : QueryUserUseCase,
    RegisterUserUseCase {
    override fun findById(id: UserId): User? =
        transaction {
            queryUserPort.findById(id)
        }

    override fun register(
        handle: String,
        nickname: String,
    ): UserId =
        transaction {
            createUserPort.create(
                CreateUserCommand(
                    handle = handle,
                    nickname = nickname,
                ),
            )
        }
}
