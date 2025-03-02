package teka.android.tekeventandroidclient.data.remote.models

import kotlinx.serialization.Serializable

@Serializable
data class WatsappSmsDto(
    val message: String
)