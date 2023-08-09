package teka.android.tekeventandroidclient.authentication.models

import kotlinx.serialization.Serializable

@Serializable
data class RegisterRequest(
    val name: String,
    val email: String,
    val password: String,
    val password_confirmation: String
    )

@Serializable
data class LoginRequest(
    val email: String,
    val password: String
)

@Serializable
data class PersonInfoRequest(
    val username: String,
    val password: String
)