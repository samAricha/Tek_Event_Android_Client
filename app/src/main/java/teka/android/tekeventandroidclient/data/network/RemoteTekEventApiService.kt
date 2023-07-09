package teka.android.tekeventandroidclient.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import teka.android.tekeventandroidclient.data.room.models.EventVisitor
import teka.android.tekeventandroidclient.data.room_remote_sync.models.CreateVisitorUpdateResponse


interface RemoteTekEventApiService {
    companion object{
        var remoteTekEventApiService:RemoteTekEventApiService ?= null
        fun getInstance(): RemoteTekEventApiService{

            if (remoteTekEventApiService == null){
               val retrofit = Retrofit.Builder()
                    .baseUrl("remote url")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

                remoteTekEventApiService = retrofit.create(RemoteTekEventApiService::class.java)
            }
            return remoteTekEventApiService!!
        }
    }

    @POST("api/eventVisitors/update")
    suspend fun updateEventVisitorList(@Body eventVisitor: EventVisitor): CreateVisitorUpdateResponse


}