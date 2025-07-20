package art.showbility.user.domain

data class User(
    val id: UserId,
    val handle: String,
    val nickname: String,
)

@JvmInline
value class UserId(
    val value: ULong,
)
