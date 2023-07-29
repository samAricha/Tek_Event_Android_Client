package teka.android.tekeventandroidclient.presentation.dashboard

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import teka.android.tekeventandroidclient.data.room_remote_sync.FetchRemoteData
import teka.android.tekeventandroidclient.repository.Repository
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(private val repository: Repository): ViewModel() {

    private val fetchRemoteData = FetchRemoteData()

    fun getRemoteDataAndSaveLocally() {
        viewModelScope.launch {
            Log.d("MY_APP", "About to make Retrofit call")
            fetchRemoteData.fetchRemoteDataAndSaveLocally(repository)
            Log.d("MY_APP", "Finished Retrofit call")
        }
    }
}