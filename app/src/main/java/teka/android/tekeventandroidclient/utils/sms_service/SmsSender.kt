package teka.android.tekeventandroidclient.utils.sms_service

import android.content.Context
import android.os.Build
import android.telephony.SmsManager

suspend fun sendSms(context: Context, phoneNumber:String, message: String){

    val smsManager:SmsManager = context.getSystemService(SmsManager::class.java)

    smsManager.sendTextMessage(phoneNumber, null, message, null, null)
}