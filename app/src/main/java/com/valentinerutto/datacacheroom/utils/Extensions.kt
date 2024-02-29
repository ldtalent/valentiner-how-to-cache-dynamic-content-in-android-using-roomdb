package com.valentinerutto.datacacheroom.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate

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