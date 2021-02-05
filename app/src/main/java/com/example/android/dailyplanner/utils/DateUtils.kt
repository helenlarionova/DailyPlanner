package com.example.android.dailyplanner.utils

import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

const val dateFormatPatternWithSlash = "dd/MM/yyyy"
const val dateFormatPatternFull = "d MMMM yyyy"
const val timeFormat = "H:mm"

fun dateToLocalDateTime(date: Date): LocalDateTime {
    return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault())
}

fun localDateTimeToDate(localDateTime: LocalDateTime): Date {
    return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant())
}