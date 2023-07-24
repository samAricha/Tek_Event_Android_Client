package teka.android.tekeventandroidclient.presentation.sendSms

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.telephony.SmsManager
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import teka.android.tekeventandroidclient.domain.interfaces.SmsSender
import teka.android.tekeventandroidclient.utils.sms_service.AppSmsSender
import javax.inject.Inject

@HiltViewModel
class SmsViewModel @Inject constructor(application: Application) : AndroidViewModel(application) {
    var phoneNumber by mutableStateOf("")
    var message by mutableStateOf("")
    @SuppressLint("StaticFieldLeak")
    val appContext: Context = getApplication<Application>().applicationContext

    private val appSmsSender = AppSmsSender(appContext)

    fun sendMessage() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                appSmsSender.sendSms(phoneNumber, message)
            } catch (e: Exception) {
                Log.e("MessageSendingError", "Failed to send messages: ${e.message}", e)
            }

        }

    }

}