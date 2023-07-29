package teka.android.tekeventandroidclient.data.room

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import teka.android.tekeventandroidclient.data.room.models.EventVisitor

@Dao
interface EventVisitorDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVisitor(eventVisitor: EventVisitor)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVisitors(eventVisitors: List<EventVisitor>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(eventVisitor: EventVisitor)

    @Delete
    suspend fun delete(eventVisitor: EventVisitor)

    @Query("SELECT * FROM event_visitors")
    fun getAllEventVisitors(): Flow<List<EventVisitor>>

    @Query("SELECT * FROM event_visitors WHERE event_visitor_id=:visitorId")
    fun getEventVisitorsById(visitorId:Int): Flow<EventVisitor>

    @Query("UPDATE event_visitors SET attended = NOT attended WHERE event_visitor_id = :visitorId")
    suspend fun toggleArrival(visitorId: Int)

}