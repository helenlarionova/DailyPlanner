package com.example.android.dailyplanner.utils

import com.example.android.dailyplanner.extensions.toStringWithFormat
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

const val dateFormatPatternWithSlash = "dd/MM/yyyy"
const val dateFormatPatternFull = "d MMMM yyyy"
const val dateFormatPatternDetail = "EEEE, MMM d, yyyy"
const val timeFormat = "HH:mm"

fun dateToLocalDateTime(date: Date): LocalDateTime {
    return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault())
}

fun localDateTimeToDate(localDateTime: LocalDateTime): Date {
    return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant())
}

fun isTimeCorrect(startTime: String, endTime: String) : Boolean{
    val formatter = SimpleDateFormat(timeFormat, Locale.getDefault())
    val inTime: Date = formatter.parse(startTime)!!
    val outTime: Date = formatter.parse(endTime)!!
    return !outTime.before(inTime)
}

fun stringToDateFormatString(str: String?): String {
    val formatter = SimpleDateFormat(dateFormatPatternWithSlash, Locale.getDefault())
    str?.let {
        try {
            val dateFromString = formatter.parse(str)
            return dateFromString.toStringWithFormat(dateFormatPatternDetail)
        }catch (e: Exception){

        }
    }
    return str.toString()
}