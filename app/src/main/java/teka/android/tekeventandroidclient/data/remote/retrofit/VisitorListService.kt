package teka.android.tekeventandroidclient.data.remote.retrofit

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import teka.android.tekeventandroidclient.data.remote.models.EventVisitorDto
import teka.android.tekeventandroidclient.data.remote.models.WatsappSmsDto
import teka.android.tekeventandroidclient.data.room.models.EventVisitor
import teka.android.tekeventandroidclient.data.room_remote_sync.models.VisitorUpdateResponse
import teka.android.tekeventandroidclient.data.room_remote_sync.models.WatsappsmsRespnse

interface VisitorListService {

    @GET("show-form")
    suspend fun getVisitorList(
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): VisitorListResponse

    @POST("eventAttendance/save")
    suspend fun updateEventVisitorList(@Body eventVisitorDto: EventVisitorDto): VisitorUpdateResponse

    @POST("eventAttendance/sendWatsappSms")
    suspend fun sendWatsappsms(@Body watsappSmsDto: WatsappSmsDto): WatsappsmsRespnse
}
