package teka.android.tekeventandroidclient.data.room_remote_sync

import android.content.Context
import android.util.Log
import android.widget.Toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import teka.android.tekeventandroidclient.data.remote.models.toEventVisitorDto
import teka.android.tekeventandroidclient.data.remote.retrofit.RetrofitProvider
import teka.android.tekeventandroidclient.repository.Repository
import teka.android.tekeventandroidclient.data.room.models.EventVisitor
import javax.inject.Inject


sealed class UpdateResult {
    data class Success(val message: String) : UpdateResult()
    data class Failure(val errorMessage: String) : UpdateResult()
}

class RemoteDataUpdater @Inject constructor(private val appContext: Context){
    //this class is responsible for taking local data to remote

    suspend fun updateRemoteEventVisitorsData(
        eventvisitors: List<EventVisitor>,
        repository: Repository
    ):UpdateResult {

        return try{
            withContext(Dispatchers.IO) {
                Log.d("updater>>>>>", eventvisitors.size.toString())
                Log.d("response>>>>>>", "response.toString()")

                eventvisitors.forEach { eventVisitor ->
                    Log.d("response>>>>>>", eventVisitor.first_name)
                    val eventVisitorDto = eventVisitor.toEventVisitorDto()
                    val response = RetrofitProvider.createVisitorListService().updateEventVisitorList(eventVisitorDto)
//                    val response = RetrofitProvider.createVisitorListService().updateEventVisitorList(eventVisitor)
                    Log.d("response2>>>>>>", response.toString())
                    if (response.isSuccessful){
                        Log.d("response3.0>>>>>>", eventVisitor.first_name)
                        eventVisitor.isBackedUp = true
                        Log.d("response3.1>>>>>>", eventVisitor.isBackedUp.toString())

                        repository.updatevisitorsList(eventVisitor)
                        UpdateResult.Success("Data updated successfully.")
                    }else{
                        Toast.makeText(appContext, "Sync failed. Please try again.", Toast.LENGTH_SHORT).show()
                    }
                }
                UpdateResult.Success("Data updated successfully.")
            }
        } catch (e: Exception) {
            UpdateResult.Failure("Error updating data: ${e.message}")
        }

    }


}