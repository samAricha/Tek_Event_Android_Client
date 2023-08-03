package teka.android.tekeventandroidclient.utils.sms_service

import android.content.Context
import android.telephony.SmsManager
import teka.android.tekeventandroidclient.domain.interfaces.SmsSender
import javax.inject.Inject

class AppSmsSender @Inject constructor(private val applicationContext: Context) : SmsSender {
    override fun sendSms(number: String, message: String) {
        val smsManager = SmsManager.getDefault()
        val countryCode = "+254";
        val officialNumber = countryCode+number;
        smsManager.sendTextMessage(officialNumber, null, message, null, null)
    }
}