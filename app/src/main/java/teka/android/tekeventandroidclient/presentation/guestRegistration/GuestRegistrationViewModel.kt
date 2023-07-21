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
import javax.inject.Inject

@HiltViewModel
class GuestRegistrationViewModel  @Inject constructor(): ViewModel() {

    var phoneNumber by mutableStateOf("")
    var guestName by mutableStateOf("")

    fun saveGuest() {

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
//                recipientDao.insertRecipient(recipient)
            }
            //recipientDao.insertRecipient(recipient)
        }

    }

}