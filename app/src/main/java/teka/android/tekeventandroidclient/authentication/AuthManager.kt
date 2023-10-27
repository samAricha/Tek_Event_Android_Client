package teka.android.tekeventandroidclient.authentication

import android.content.Context
import android.widget.Toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import teka.android.tekeventandroidclient.authentication.models.LoginRequest
import teka.android.tekeventandroidclient.authentication.models.RegisterRequest
import teka.android.tekeventandroidclient.data.dataStore.DataStoreRepository
import teka.android.tekeventandroidclient.data.remote.retrofit.AuthService
import teka.android.tekeventandroidclient.data.remote.retrofit.RetrofitProvider
import javax.inject.Inject


class AuthManager @Inject constructor(
                  private val dataStoreRepository: DataStoreRepository,
                  private val context: Context
) {

    private val authService: AuthService = RetrofitProvider.createAuthService()


    suspend fun login(email: String, password: String): Boolean {

        try {
            val response = authService.login(LoginRequest(email, password))
            if (response.isSuccessful) {
                val token = response.data.accessToken
                saveAuthToken(token)
                return true
            }
        }catch (e: Exception) {
            // Handle the exception here, e.g., log it or perform error handling.
            e.printStackTrace()
            Toast.makeText(context, "Login failed. Please try again.", Toast.LENGTH_SHORT).show()

        }
        return false
    }

    suspend fun register(
        name: String,
        phone: String,
        email: String,
        password: String,
        passwordConfirmation: String
    ): Boolean {
        try{

            val response = authService.registration(
                RegisterRequest(
                    name = name,
                    email = email,
                    phone = phone,
                    password = password,
                    password_confirmation = passwordConfirmation)
            )
            if (response.isSuccessful) {
                val token = response.data.accessToken
                saveAuthToken(token)
                return true
            }
        }catch (e: Exception) {
            // Handle the exception here, e.g., log it or perform error handling.
            e.printStackTrace()
            Toast.makeText(context, "Registration failed. Please try again.", Toast.LENGTH_SHORT).show()
        }
        return false
    }

    private suspend fun saveAuthToken(token: String) = withContext(Dispatchers.IO) {
        dataStoreRepository.saveToken(token)
    }

    suspend fun getAuthToken(): String {
        return dataStoreRepository.getAccessToken.first()
    }

    suspend fun isAuthenticated(): Boolean{
        val isAuthenticated = dataStoreRepository.readLoggedInState()
        return isAuthenticated.first()
    }

    suspend fun clearAuthToken() {
        dataStoreRepository.saveToken("")
    }
}
