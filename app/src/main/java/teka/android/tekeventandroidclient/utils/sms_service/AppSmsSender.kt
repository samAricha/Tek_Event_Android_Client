package teka.android.tekeventandroidclient.utils.sms_service

import android.content.Context
import android.telephony.SmsManager
import teka.android.tekeventandroidclient.domain.interfaces.SmsSender
import javax.inject.Inject

class AppSmsSender @Inject constructor(private val applicationContext: Context) : SmsSender {
    override fun sendSms(number: String, message: String) {
        val smsManager = SmsManager.getDefault()
        smsManager.sendTextMessage("+254708392326", null, message, null, null)
    }
}