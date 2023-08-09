package teka.android.tekeventandroidclient.presentation.splashScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import teka.android.tekeventandroidclient.authentication.AuthManager
import teka.android.tekeventandroidclient.data.dataStore.DataStoreRepository
import teka.android.tekeventandroidclient.data.remote.retrofit.RetrofitProvider
import teka.android.tekeventandroidclient.presentation.auth.AuthViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
) : ViewModel() {

    private val mutableStateFlow = MutableStateFlow(true)
    val isLoading = mutableStateFlow.asStateFlow()

    init {
        viewModelScope.launch {
            val authService = RetrofitProvider.createAuthService()
            val authManager = AuthManager(authService, dataStoreRepository)
            val authViewModel = AuthViewModel(authManager)

            delay(100)
            mutableStateFlow.value = false
        }
    }


}