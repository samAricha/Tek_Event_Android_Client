package teka.android.tekeventandroidclient.presentation.guestRegistration

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import teka.android.tekeventandroidclient.data.room.EventVisitorDao
import teka.android.tekeventandroidclient.data.room.models.EventVisitor
import javax.inject.Inject

@HiltViewModel
class GuestRegistrationViewModel  @Inject constructor(private val eventVisitorDao: EventVisitorDao): ViewModel() {

    var phoneNumber by mutableStateOf("")
    var guestName by mutableStateOf("")

    fun saveGuest() {

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val visitor = EventVisitor(first_name =  guestName, phone = phoneNumber)
                eventVisitorDao.insertVisitor(visitor)
            }
        }

    }

}