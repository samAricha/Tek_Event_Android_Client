package teka.android.tekeventandroidclient.presentation.splashScreen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
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
import teka.android.tekeventandroidclient.navigation.AUTH_GRAPH_ROUTE
import teka.android.tekeventandroidclient.navigation.To_MAIN_GRAPH_ROUTE
import teka.android.tekeventandroidclient.presentation.auth.AuthViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
) : ViewModel() {

    private val mutableStateFlow = MutableStateFlow(true)
//    val isLoading = mutableStateFlow.asStateFlow()

    private val _isLoading: MutableState<Boolean> = mutableStateOf(true)
    val isLoading: State<Boolean> = _isLoading

    private val _startDestination = mutableStateOf<String?>(null)
    val startDestination: MutableState<String?> = _startDestination

    init {
        viewModelScope.launch {
//            val authService = RetrofitProvider.createAuthService()
//            val authManager = AuthManager(authService, dataStoreRepository)
//            val authViewModel = AuthViewModel(authManager)

            dataStoreRepository.readLoggedInState().collect { completed ->
                if (completed) {
                    _startDestination.value = To_MAIN_GRAPH_ROUTE
                } else {
                    _startDestination.value = AUTH_GRAPH_ROUTE
                }
            }
            _isLoading.value = false

        }
    }


}