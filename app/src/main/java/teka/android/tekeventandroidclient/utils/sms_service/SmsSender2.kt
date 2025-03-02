package teka.android.tekeventandroidclient.utils.sms_service

import android.content.Context
import android.telephony.SmsManager

fun sendSms(context: Context, phoneNumber:String, message: String){

    val smsManager:SmsManager = context.getSystemService(SmsManager::class.java)

    smsManager.sendTextMessage("+254708392326", null, message, null, null)
}