package teka.android.tekeventandroidclient.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import teka.android.tekeventandroidclient.data.room.models.EventVisitor

@Database(
    entities = [EventVisitor::class],
    version = 1,
    exportSchema = false
)
abstract class TekEventDatabase: RoomDatabase() {

    abstract fun eventVisitorDao():EventVisitorDao

    companion object{
        @Volatile//this is creating the db in a thread safe manner
        var INSTANCE: TekEventDatabase? = null
        fun getDatabase(context: Context): TekEventDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context,
                    TekEventDatabase::class.java,
                    "tek_event_db"
                ).build()
                INSTANCE= instance
                return instance
            }
        }
    }



}