package art.showbility.user.adapter.web

object UserRequest {
    data class RegisterTestUserRequest(
        val handle: String,
        val nickname: String,
    )

    data class UpdateMyselfRequest(
        val nickname: String,
    )
}
