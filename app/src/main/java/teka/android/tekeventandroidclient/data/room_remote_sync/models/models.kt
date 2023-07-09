package teka.android.tekeventandroidclient.data.room_remote_sync.models

import teka.android.tekeventandroidclient.data.room.models.EventVisitor


data class CreateVisitorUpdateResponse(
    val success: Boolean,
    val message: String?,
    val data: EventVisitor?
)