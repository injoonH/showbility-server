package art.showbility.user.app.port.outbound

import art.showbility.user.domain.User
import art.showbility.user.domain.UserId

interface QueryUserPort {
    fun findById(id: UserId): User?

    fun getById(id: UserId): User

    fun checkExistsById(id: UserId)
}
