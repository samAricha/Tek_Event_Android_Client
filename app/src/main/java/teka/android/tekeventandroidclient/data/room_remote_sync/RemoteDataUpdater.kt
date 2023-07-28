package teka.android.tekeventandroidclient.data.room_remote_sync

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import teka.android.tekeventandroidclient.data.remote.retrofit.RetrofitProvider
import teka.android.tekeventandroidclient.repository.Repository
import teka.android.tekeventandroidclient.data.room.models.EventVisitor


class RemoteDataUpdater {

    suspend fun updateEventVisitorsData(eventvisitors: List<EventVisitor>, repository: Repository) {
        withContext(Dispatchers.IO) {
            try {
                eventvisitors.forEach { eventVisitor ->
                    val response = RetrofitProvider.createVisitorListService().updateEventVisitorList(eventVisitor)
                    if (response != null) {
                        if (response.success){
                            eventVisitor.isBackedUp = true
                            repository.updatevisitorsList(eventVisitor)
                        }
                    }

                }
                // Handle the response if needed
            } catch (e: Exception) {
                // Handle the error
            }
        }
    }


}