package teka.android.tekeventandroidclient.presentation.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import teka.android.tekeventandroidclient.authentication.AuthManager
import teka.android.tekeventandroidclient.data.dataStore.DataStoreRepository
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authManager: AuthManager,
    private val dataStoreRepository: DataStoreRepository
    ) : ViewModel() {

    private var _isAuthenticated = MutableStateFlow(false)
    val isAuthenticated: StateFlow<Boolean> = _isAuthenticated

    private var _isLoggedIn = MutableStateFlow(false)
    val isLoggedIn: StateFlow<Boolean> = _isLoggedIn

    private val _isRegistered = MutableStateFlow(false)
    val isRegistered: StateFlow<Boolean> = _isRegistered

    fun login(email: String, password: String) {
        viewModelScope.launch {
            val success = authManager.login(email, password)
            _isLoggedIn.value = success
            if (success)dataStoreRepository.saveLoggedInState(isLoggedIn = success)
        }
    }

    fun register(name: String, email: String, password: String, passwordConfirmation: String) {
        viewModelScope.launch {
            val success = authManager.register(name, email, password, passwordConfirmation)
            _isRegistered.value = success
            if (success)dataStoreRepository.saveLoggedInState(isLoggedIn = success)
        }
    }

    fun logout() {
        viewModelScope.launch {
            authManager.clearAuthToken()
            _isAuthenticated.value = false
        }

    }

}