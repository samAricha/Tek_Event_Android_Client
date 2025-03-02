package teka.android.tekeventandroidclient.data.remote.retrofit

import kotlinx.serialization.Serializable
import teka.android.tekeventandroidclient.data.room.models.EventVisitor

@Serializable
data class VisitorListResponse(
    val page: Int? = null,
    val results: List<VisitorListResult>,
    val total_pages: Int,
    val total_results: Int
)

@Serializable
data class VisitorListResult(
    val page: Int? = null,
    val first_name: String,
    val second_name: String,
    val email: String,
    val phone: String,
)

fun VisitorListResult.toEventVisitor(): EventVisitor {
    return EventVisitor(
        first_name = this.first_name,
        second_name = this.second_name,
        email = this.email,
        phone = this.phone,
    )
}