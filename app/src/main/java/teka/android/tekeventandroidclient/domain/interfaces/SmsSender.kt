package teka.android.tekeventandroidclient.domain.interfaces

interface SmsSender {
    fun sendSms(number: String, message: String)
}