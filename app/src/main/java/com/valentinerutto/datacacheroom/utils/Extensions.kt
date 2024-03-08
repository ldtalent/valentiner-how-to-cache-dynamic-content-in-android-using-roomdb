package com.valentinerutto.datacacheroom.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Locale

fun String?.orUnknown(unknown: String): String {
    return if (this.isNullOrEmpty()) unknown else this
}

@RequiresApi(Build.VERSION_CODES.O)
fun String?.dateFormat(): String {
    return LocalDate.parse(
        this?.substring(0, 10)
    ).toString()
}


fun <T> T?.orUnknown(defaultValue: T): T {
    return this ?: defaultValue
}
fun todayInMillis() = System.currentTimeMillis()

fun convertToDate(date: Long): String =
    SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(date)

fun convertToLong(date: String): Long = SimpleDateFormat(
    "yyyy-MM-dd HH:mm:ss",
    Locale.ENGLISH
).parse(date)?.time ?: 0L

fun todayInDate(): String = convertToDate(todayInMillis())