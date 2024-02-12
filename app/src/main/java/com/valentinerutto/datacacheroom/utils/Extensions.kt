package com.valentinerutto.datacacheroom.utils

fun String?.orUnknown(unknown: String): String {
    return this ?: unknown
}

fun <T> T?.orUnknown(defaultValue: T): T {
    return this ?: defaultValue
}