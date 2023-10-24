package teka.android.tekeventandroidclient.data.room_remote_sync.models

import kotlinx.serialization.Serializable

@Serializable
data class VisitorUpdateResponse(
    val isSuccessful: Boolean,
    val message: String?,
)

@Serializable
data class WatsappsmsRespnse(
    val isSuccessful: Boolean,
    val message: String?,
)

