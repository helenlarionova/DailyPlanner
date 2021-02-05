package com.example.android.dailyplanner.extensions

import android.text.Editable
import com.example.android.dailyplanner.utils.dateToLocalDateTime
import com.example.android.dailyplanner.utils.localDateTimeToDate
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.*

fun String.toEditable(): Editable =  Editable.Factory.getInstance().newEditable(this)

fun Int.twoDigits() = if (this <= 9) "0$this" else this.toString()

fun Date.toStringWithFormat(format: String) : String = SimpleDateFormat(format, Locale.getDefault()).format(this)

fun Long.toDate() : Date = Date(this)



fun Date.atStartOfDay() : Date?{
        val localDateTime: LocalDateTime = dateToLocalDateTime(this)
        val startOfDay: LocalDateTime = localDateTime.with(LocalTime.MIN)
        return localDateTimeToDate(startOfDay)
    }

fun Date.atEndOfDay() : Date? {
    val localDateTime: LocalDateTime = dateToLocalDateTime(this)
    val endOfDay: LocalDateTime = localDateTime.with(LocalTime.MAX)
    return localDateTimeToDate(endOfDay)
}







