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
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import teka.android.tekeventandroidclient.data.room.EventVisitorDao
import teka.android.tekeventandroidclient.data.room.models.EventVisitor
import teka.android.tekeventandroidclient.domain.interfaces.SmsSender
import teka.android.tekeventandroidclient.utils.sms_service.AppSmsSender
import javax.inject.Inject

@HiltViewModel
class SmsViewModel @Inject constructor(private val application: Application, private val eventVisitorDao: EventVisitorDao,) : AndroidViewModel(application) {

    var phoneNumber by mutableStateOf("")
    var message by mutableStateOf("")

    private val appSmsSender = AppSmsSender(application)

    suspend fun fetchAllVisitors(): List<EventVisitor> {
        return withContext(Dispatchers.IO) {
            eventVisitorDao.getAllEventVisitors()
                .map { it.reversed() }
                .flowOn(Dispatchers.IO)
                .toList()
                .flatten()
        }

    }



    fun sendMessage() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
//                appSmsSender.sendSms("+254708392326", message)
                val eventVisitors: List<EventVisitor> = fetchAllVisitors()
                Log.e("SMSVIEWMODEL",  "fetched visitors")
                appSmsSender.sendMultipleSms(eventVisitors, "hii everyone")
            } catch (e: Exception) {
                Log.e("MessageSendingError", "Failed to send messages: ${e.message}", e)
            }

        }

    }

}