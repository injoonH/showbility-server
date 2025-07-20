package art.showbility.user.app.service

import art.showbility.user.app.port.inbound.QueryUserUseCase
import art.showbility.user.app.port.inbound.RegisterUserUseCase
import art.showbility.user.app.port.inbound.UpdateUserUseCase
import art.showbility.user.app.port.outbound.CreateUserCommand
import art.showbility.user.app.port.outbound.CreateUserPort
import art.showbility.user.app.port.outbound.QueryUserPort
import art.showbility.user.app.port.outbound.UpdateUserCommand
import art.showbility.user.app.port.outbound.UpdateUserPort
import art.showbility.user.domain.User
import art.showbility.user.domain.UserId
import org.jetbrains.exposed.v1.jdbc.transactions.transaction
import org.springframework.stereotype.Service

@Service
class UserService(
    private val queryUserPort: QueryUserPort,
    private val createUserPort: CreateUserPort,
    private val updateUserPort: UpdateUserPort,
) : QueryUserUseCase,
    RegisterUserUseCase,
    UpdateUserUseCase {
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

    override fun updateUser(
        id: UserId,
        nickname: String,
    ) {
        check(nickname.isNotBlank())
        transaction {
            updateUserPort.updateUser(
                UpdateUserCommand(
                    id = id,
                    nickname = nickname,
                ),
            )
        }
    }
}
