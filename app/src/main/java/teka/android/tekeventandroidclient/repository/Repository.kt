package teka.android.tekeventandroidclient.repository

import teka.android.tekeventandroidclient.data.room.EventVisitorDao
import teka.android.tekeventandroidclient.data.room.models.EventVisitor


class Repository(
    private val eventVisitorDao: EventVisitorDao,
) {
    //the following are methods which are going to help us get our data.
    val getAllEventVisitors = eventVisitorDao.getAllEventVisitors()

    suspend fun updatevisitorsList(eventVisitor: EventVisitor){

        eventVisitorDao.update(eventVisitor = eventVisitor)

    }

    suspend fun saveRemotevisitorList(){
        //get all event visitors from remote and save them locally
    }
}