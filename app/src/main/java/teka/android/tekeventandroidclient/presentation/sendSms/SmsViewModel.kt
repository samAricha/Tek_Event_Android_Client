package teka.android.tekeventandroidclient.presentation.sendSms

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SmsViewModel @Inject constructor() : ViewModel() {
    var phoneNumber by mutableStateOf("")
    var message by mutableStateOf("")

    fun sendMessage(context: Context) {}

}