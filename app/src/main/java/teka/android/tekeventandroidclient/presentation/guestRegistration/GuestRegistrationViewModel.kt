package teka.android.tekeventandroidclient.presentation.guestRegistration

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import teka.android.tekeventandroidclient.data.room.EventVisitorDao
import teka.android.tekeventandroidclient.data.room.models.EventVisitor
import teka.android.tekeventandroidclient.utils.sms_service.AppSmsSender
import teka.android.tekeventandroidclient.utils.trimToLastNineDigits
import javax.inject.Inject

@HiltViewModel
class GuestRegistrationViewModel  @Inject constructor(private val eventVisitorDao: EventVisitorDao, private val application: Application): ViewModel() {

    private val appSmsSender = AppSmsSender(application)
    val allVisitors: Flow<List<EventVisitor>> = eventVisitorDao.getAllEventVisitors()
        .map { it.reversed() }
        .flowOn(Dispatchers.IO)



    var phoneNumber by mutableStateOf("")
    var guestName by mutableStateOf("")

    fun saveGuest() {
        // Split the guestName into words using space as the delimiter
        val words = guestName.split(" ")

        var firstName = ""
        var secondName = ""

        // Check if there is at least one word
        if (words.isNotEmpty()) {
            firstName = words[0]
        }

        // Check if there is a second word
        if (words.size >= 2) {
            secondName = words[1]
        }


        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val visitor = EventVisitor(first_name =  firstName, second_name= secondName, phone = phoneNumber)
                eventVisitorDao.insertVisitor(visitor)
                val originalString = phoneNumber
                val trimmedString = trimToLastNineDigits(originalString)
                val capitalizedVisitorName = guestName.replaceFirstChar { it.uppercaseChar() }
//                appSmsSender.sendSms(trimmedString, "Hi $capitalizedVisitorName welcome to Coders Club Meeting")
            }
        }

    }


//    fun trimToLastNineDigits(input: String): String {
//        return if (input.length > 9) {
//            input.substring(input.length - 9)
//        } else {
//            input
//        }
//    }

}