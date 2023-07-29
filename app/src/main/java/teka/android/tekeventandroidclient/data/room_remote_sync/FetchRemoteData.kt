package teka.android.tekeventandroidclient.data.room_remote_sync

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import teka.android.tekeventandroidclient.data.remote.retrofit.RetrofitProvider
import teka.android.tekeventandroidclient.data.remote.retrofit.toEventVisitor
import teka.android.tekeventandroidclient.data.room.models.EventVisitor
import teka.android.tekeventandroidclient.repository.Repository

class FetchRemoteData {
    suspend fun fetchRemoteDataAndSaveLocally(repository: Repository){
        withContext(Dispatchers.IO) {
            try {
                Log.d("INSIDE TRY", "FIRST LINE")
                val response = RetrofitProvider.createVisitorListService().getVisitorList()
                val eventVisitor: List<EventVisitor> = response.results.map { it.toEventVisitor() }
                val repositoryResponse = repository.saveRemotevisitorList(eventVisitor)
                Log.d("REPOSITORY RESPONSE", repositoryResponse.toString())


            } catch (e: Exception) {
                Log.d("FETCH_ERROR", e.message.toString())

            }
        }
    }
}