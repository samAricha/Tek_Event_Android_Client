package teka.android.tekeventandroidclient.utils

fun trimToLastNineDigits(input: String): String {
    return if (input.length > 9) {
        input.substring(input.length - 9)
    } else {
        input
    }
}