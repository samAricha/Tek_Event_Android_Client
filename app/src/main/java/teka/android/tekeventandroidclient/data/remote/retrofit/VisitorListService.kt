package teka.android.tekeventandroidclient.data.remote.retrofit

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import teka.android.tekeventandroidclient.data.room.models.EventVisitor
import teka.android.tekeventandroidclient.data.room_remote_sync.models.VisitorUpdateResponse

interface VisitorListService {


    @GET("/api/eventVisitors/fetch")
    suspend fun getVisitorList(
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): VisitorListResponse

    @POST("api/eventVisitors/update")
    suspend fun updateEventVisitorList(@Body eventVisitor: EventVisitor): VisitorUpdateResponse


}