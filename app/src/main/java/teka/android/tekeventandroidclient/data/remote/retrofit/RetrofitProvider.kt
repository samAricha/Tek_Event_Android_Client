package teka.android.tekeventandroidclient.data.remote.retrofit

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

object RetrofitProvider {
    private const val TEST_URL = "https://1004-105-161-198-102.ngrok-free.app/api/"
    private const val BASE_URL = "http://191.101.0.246:8003/api/"

    private fun provide(): Retrofit {
        val json = Json { ignoreUnknownKeys = true }//to ignore unkown keys

        return Retrofit.Builder()
            .baseUrl(TEST_URL)
            .client(provideOkhttpClient())
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    private fun provideOkhttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().also {
                it.level = HttpLoggingInterceptor.Level.BODY
            })
            .addInterceptor(HeaderInterceptor)
            .build()


    fun createVisitorListService(): VisitorListService {
        return provide().create(VisitorListService::class.java)
    }

    fun createWatsappSmsService(): VisitorListService {
        return provide().create(VisitorListService::class.java)
    }

    fun createAuthService(): AuthService {
        return provide().create(AuthService::class.java)
    }
}