package teka.android.tekeventandroidclient.repository

import teka.android.tekeventandroidclient.data.room.EventVisitorDao
import teka.android.tekeventandroidclient.data.room.models.EventVisitor
import javax.inject.Inject


class Repository @Inject constructor(
    private val eventVisitorDao: EventVisitorDao,
) {
    //the following are methods which are going to help us get our data.
    val getAllEventVisitors = eventVisitorDao.getAllEventVisitors()

    suspend fun updatevisitorsList(eventVisitor: EventVisitor){
        eventVisitorDao.update(eventVisitor = eventVisitor)
    }

    suspend fun saveRemotevisitorList(visitors: List<EventVisitor>){
        eventVisitorDao.insertVisitors(visitors)
    }
}