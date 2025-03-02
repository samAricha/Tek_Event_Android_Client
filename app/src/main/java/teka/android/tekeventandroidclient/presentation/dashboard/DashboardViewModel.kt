package teka.android.tekeventandroidclient.presentation.dashboard


import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import teka.android.tekeventandroidclient.data.room.models.EventVisitor
import teka.android.tekeventandroidclient.data.room_remote_sync.FetchRemoteData
import teka.android.tekeventandroidclient.data.room_remote_sync.RemoteDataUpdater
import teka.android.tekeventandroidclient.data.room_remote_sync.UpdateResult
import teka.android.tekeventandroidclient.repository.Repository
import javax.inject.Inject

data class SnackbarData(val message: String)


@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val repository: Repository,
    private val remoteDataUpdater: RemoteDataUpdater
    ): ViewModel() {

    private val fetchRemoteData = FetchRemoteData()

    private val _localEventVisitors = MutableStateFlow<List<EventVisitor>>(emptyList())
    val localEventVisitors: StateFlow<List<EventVisitor>> = _localEventVisitors.asStateFlow()

    val snackbarMessage = mutableStateOf<String?>(null)

    private val _snackbarData = MutableStateFlow<SnackbarData?>(null)
    val snackbarData: StateFlow<SnackbarData?> = _snackbarData

    private val _isSyncing = MutableStateFlow(false)
    val isSyncing: StateFlow<Boolean> = _isSyncing


    init {
        viewModelInitialization()
    }

    fun viewModelInitialization(){
        fetchVisitorsList()
    }

    private fun fetchVisitorsList() {
        viewModelScope.launch {
            repository.getAllEventVisitors.collectLatest { visitors ->
                _localEventVisitors.value = visitors
            }
        }
    }


    // Function to trigger a Snackbar
    fun showSnackbar(message: String) {
        _snackbarData.value = SnackbarData(message)
    }

    // Function to clear the Snackbar
    fun clearSnackbar() {
        _snackbarData.value = null
    }


    fun getRemoteDataAndSaveLocally() {
        viewModelScope.launch {
            Log.d("MY_APP", "About to make Retrofit call")
            fetchRemoteData.fetchRemoteDataAndSaveLocally(repository)
            Log.d("MY_APP", "Finished Retrofit call")
        }
    }

    fun syncRoomDbToRemote() {
        Log.d("testing", "our test")
        viewModelScope.launch {
            _isSyncing.value = true
//            fetchRemoteData.fetchRemoteDataAndSaveLocally(repository)
            val notBackedUpEventVisitorsList = localEventVisitors.value.filter { eventVisitors ->
                !eventVisitors.isBackedUp
            }
            Log.d("testingnbe", notBackedUpEventVisitorsList.size.toString())
            val remoteResult = remoteDataUpdater
                .updateRemoteEventVisitorsData(
                    notBackedUpEventVisitorsList,
                    repository)
            Log.d("testingrslts", remoteResult.toString())
            when (remoteResult) {
                is UpdateResult.Success -> {
                    snackbarMessage.value = remoteResult.message
                    showSnackbar(snackbarMessage.value!!)
                }
                is UpdateResult.Failure -> {
                    snackbarMessage.value = remoteResult.errorMessage
                    showSnackbar(snackbarMessage.value!!)
                }
            }

            Log.d("MY_APP", "Finished Retrofit call")
        }
    }
}