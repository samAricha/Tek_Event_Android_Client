package teka.android.tekeventandroidclient.data.room.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "event_visitors")
data class EventVisitor(
    @ColumnInfo(name = "event_visitor_id")
    @PrimaryKey
    val id: Int = 0,
    val uuid: String = generateUniqueId(),
    val fist_name: String,
    val second_name: String,
    val email: String,
    val phone: String,
    var isBackedUp: Boolean = false,
    val createdAt: Long = System.currentTimeMillis()
) {
    companion object {
        private fun generateUniqueId(): String {
            return UUID.randomUUID().toString()
        }
    }
}