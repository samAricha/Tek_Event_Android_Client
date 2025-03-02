package teka.android.tekeventandroidclient.data.remote.models

import kotlinx.serialization.Serializable
import teka.android.tekeventandroidclient.data.room.models.EventVisitor

@Serializable
data class EventVisitorDto(
    val uuid: String,
    val first_name: String,
    val second_name: String,
    val email: String,
    val phone: String,
    val createdAt: Long
)

fun EventVisitor.toEventVisitorDto(): EventVisitorDto {
    return EventVisitorDto(
        uuid = this.uuid,
        first_name = this.first_name,
        second_name = this.second_name ?: "",
        email = this.email ?: "",
        phone = this.phone,
        createdAt = this.createdAt
    )
}
