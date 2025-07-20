package art.showbility.auth.domain

import art.showbility.user.domain.UserId

data class AuthPrincipal(
    val userId: UserId,
)
