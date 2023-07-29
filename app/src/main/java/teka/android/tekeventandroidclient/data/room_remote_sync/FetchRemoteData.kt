package teka.android.tekeventandroidclient.data.room_remote_sync

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import teka.android.tekeventandroidclient.data.remote.retrofit.RetrofitProvider
import teka.android.tekeventandroidclient.data.remote.retrofit.toEventVisitor
import teka.android.tekeventandroidclient.data.room.models.EventVisitor
import teka.android.tekeventandroidclient.repository.Repository

class FetchRemoteData {
    //this class is responsible for bringing remote data to device
    //get a list of event visitors and save them locally
    suspend fun fetchRemoteDataAndSaveLocally(repository: Repository){
        //get a list of remote visitors and save them to room DB
        withContext(Dispatchers.IO) {
            try {

                val response = RetrofitProvider.createVisitorListService().getVisitorList()
                val eventVisitor: List<EventVisitor> = response.results.map { it.toEventVisitor() }

                repository.saveRemotevisitorList(eventVisitor)

            } catch (e: Exception) {
                // Handle the error
            }
        }
    }
}