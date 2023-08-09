package teka.android.tekeventandroidclient.presentation.attendees

import android.app.Application
import android.media.metrics.Event
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import teka.android.tekeventandroidclient.data.room.EventVisitorDao
import teka.android.tekeventandroidclient.data.room.models.EventVisitor
import teka.android.tekeventandroidclient.utils.sms_service.AppSmsSender
import teka.android.tekeventandroidclient.utils.trimToLastNineDigits
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class AttendeeViewModel @Inject constructor(private val eventVisitorDao: EventVisitorDao, private val application: Application): ViewModel() {

    // Flow for search query
    private val searchQuery = MutableStateFlow("")
    private val appSmsSender = AppSmsSender(application)

    // A combined flow of all visitors and search query
    val filteredVisitors: Flow<List<EventVisitor>> = combine(
        eventVisitorDao.getAllEventVisitors().map { it.reversed() },
        searchQuery
    ) { visitors, query ->
        if (query.isEmpty()) {
            visitors
        } else {
            visitors.filter {
                it.first_name.contains(query, true) ||
                        (it.second_name?.contains(query, true) ?: false) ||
                        (it.phone?.contains(query, true) ?: false)
            }
        }
    }.flowOn(Dispatchers.IO)

    fun onSearchQueryChanged(query: String) {
        searchQuery.value = query
    }

    fun toggleArrivalStatus(visitorId: Int, visitorName: String) {
        val capitalizedVisitorName = visitorName.replaceFirstChar { it.uppercaseChar() }
        viewModelScope.launch {
            eventVisitorDao.toggleArrival(visitorId)
            val eventVisitor:EventVisitor = eventVisitorDao.getEventVisitorsById(visitorId).first()
            val originalString = eventVisitor.phone
            val trimmedNumber = trimToLastNineDigits(originalString)
            appSmsSender.sendSms(trimmedNumber, "Hi $capitalizedVisitorName welcome to Coders Club Meeting")
        }
    }
}