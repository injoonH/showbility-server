package art.showbility.user.app.port.inbound

import art.showbility.user.domain.User
import art.showbility.user.domain.UserId

interface QueryUserUseCase {
    fun findById(id: UserId): User?
}
