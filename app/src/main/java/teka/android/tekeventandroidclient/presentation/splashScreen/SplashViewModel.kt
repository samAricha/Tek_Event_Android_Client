package teka.android.tekeventandroidclient.presentation.splashScreen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import teka.android.tekeventandroidclient.data.dataStore.DataStoreRepository
import teka.android.tekeventandroidclient.navigation.AUTH_GRAPH_ROUTE
import teka.android.tekeventandroidclient.navigation.To_MAIN_GRAPH_ROUTE
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val repository: DataStoreRepository
) : ViewModel() {

    private val _isLoading: MutableState<Boolean> = mutableStateOf(true)
    val isLoading: State<Boolean> = _isLoading

    private val _startDestination = mutableStateOf<String?>(null)
    val startDestination: MutableState<String?> = _startDestination

    init {
        viewModelScope.launch {
            repository.readOnBoardingState().collect { completed ->
//                if (completed) {
//                    _startDestination.value = To_MAIN_GRAPH_ROUTE
//                } else {
//                    _startDestination.value = AUTH_GRAPH_ROUTE
//                }
                _startDestination.value = To_MAIN_GRAPH_ROUTE
            }
            _isLoading.value = false

        }
    }

}