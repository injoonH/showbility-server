package art.showbility.user.adapter.persistence

import art.showbility.user.app.port.outbound.CreateUserCommand
import art.showbility.user.app.port.outbound.CreateUserPort
import art.showbility.user.app.port.outbound.QueryUserPort
import art.showbility.user.app.port.outbound.UpdateUserCommand
import art.showbility.user.app.port.outbound.UpdateUserPort
import art.showbility.user.domain.User
import art.showbility.user.domain.UserId
import org.jetbrains.exposed.v1.core.ResultRow
import org.jetbrains.exposed.v1.jdbc.insertAndGetId
import org.jetbrains.exposed.v1.jdbc.select
import org.jetbrains.exposed.v1.jdbc.selectAll
import org.jetbrains.exposed.v1.jdbc.update
import org.springframework.stereotype.Repository

@Repository
class UserRepository :
    QueryUserPort,
    CreateUserPort,
    UpdateUserPort {
    override fun findById(id: UserId): User? =
        UserTable
            .selectAll()
            .where { UserTable.id eq id.value }
            .singleOrNull()
            ?.toUser()

    override fun getById(id: UserId): User = findById(id) ?: throwUserNotFound(id)

    override fun checkExistsById(id: UserId) {
        UserTable
            .select(UserTable.id)
            .where { UserTable.id eq id.value }
            .singleOrNull() ?: throwUserNotFound(id)
    }

    override fun create(command: CreateUserCommand): UserId =
        UserTable
            .insertAndGetId {
                it[handle] = command.handle
                it[nickname] = command.nickname
            }.let { UserId(it.value) }

    override fun updateUser(command: UpdateUserCommand) {
        UserTable.update({ UserTable.id eq command.id.value }) {
            it[nickname] = command.nickname
        }
    }

    private fun ResultRow.toUser(): User =
        User(
            id = UserId(this[UserTable.id].value),
            handle = this[UserTable.handle],
            nickname = this[UserTable.nickname],
        )

    private fun throwUserNotFound(id: UserId): Nothing = throw NoSuchElementException("User with id ${id.value} not found.")
}
