package teka.android.tekeventandroidclient.data.remote.retrofit

import retrofit2.http.Body
import retrofit2.http.POST
import teka.android.tekeventandroidclient.authentication.models.AuthResponse
import teka.android.tekeventandroidclient.authentication.models.LoginRequest
import teka.android.tekeventandroidclient.authentication.models.PersonInfoRequest
import teka.android.tekeventandroidclient.authentication.models.RegisterRequest
import teka.android.tekeventandroidclient.authentication.models.User

interface AuthService {

    @POST("auth/register")
    suspend fun registration(
        @Body registerRequest: RegisterRequest
    ): AuthResponse

    @POST("auth/login")
    suspend fun login(
        @Body loginRequest: LoginRequest
    ): AuthResponse

    @POST("me")
    suspend fun getMeInfo(
        @Body personInfoRequest: PersonInfoRequest
    ): User
}