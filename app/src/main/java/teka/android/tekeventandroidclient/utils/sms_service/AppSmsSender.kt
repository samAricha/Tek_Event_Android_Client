package teka.android.tekeventandroidclient.utils.sms_service

import android.content.Context
import android.os.Build
import android.telephony.SmsManager
import android.util.Log
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import teka.android.tekeventandroidclient.data.room.models.EventVisitor
import teka.android.tekeventandroidclient.domain.interfaces.SmsSender
import teka.android.tekeventandroidclient.utils.trimToLastNineDigits
import javax.inject.Inject

class AppSmsSender @Inject constructor(private val applicationContext: Context) : SmsSender {


    override fun sendSms(number: String, message: String) {
        val smsManager = SmsManager.getDefault()
//        lateinit var smsManager:SmsManager
        val countryCode = "+254";
        val officialNumber = countryCode+number;
//        smsManager = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
//            applicationContext.getSystemService<SmsManager>(SmsManager::class.java)
//        } else {
//            SmsManager.getDefault()
//        }
        smsManager.sendTextMessage(officialNumber, null, message, null, null)
    }





    fun sendMultipleSms(eventVisitors: List<EventVisitor>, message: String){
        val coroutineScope = CoroutineScope(Dispatchers.IO)
        Log.e("InSendMultipleSms",  "inside")



        coroutineScope.launch {
            try {
                val recipients = eventVisitors

                val sendJobs = recipients.map { recipient ->
                    val phoneNumber = trimToLastNineDigits(recipient.phone)
                    val message = "Hello, this is a broadcast message!"

                    launch {
                        try {
                            val smsManager: SmsManager = SmsManager.getDefault()
                            smsManager.sendTextMessage(phoneNumber, null, message, null, null)
                        } catch (e: Exception) {
                            Log.e("MessageSendingError", "Failed to send message to $phoneNumber: ${e.message}", e)
                        }
                    }
                }

                sendJobs.forEach { it.join() } // Wait for all sending operations to complete

                showToastOnMainThread("All messages sent", applicationContext)
            } catch (e: Exception) {
                Log.e("MessageSendingError", "Failed to send messages: ${e.message}", e)
                showToastOnMainThread("Failed to send messages: ${e.message}", applicationContext)
            }
        }
        coroutineScope.cancel()
    }


    private fun showToastOnMainThread(message: String, context: Context) {
        CoroutineScope(Dispatchers.Main).launch {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }
}