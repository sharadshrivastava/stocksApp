package com.test.app.data.common

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

fun Date.parseToString(format: String = "d MMM yyyy, HH:mm:ss"): String? = try {
    SimpleDateFormat(format, Locale.ENGLISH).format(this)
} catch (e: ParseException) {
    null
}