package com.example.android.dailyplanner.utils

import com.example.android.dailyplanner.entity.Event
import com.example.android.dailyplanner.entity.EventRepo
import com.example.android.dailyplanner.extensions.toStringWithFormat


fun convertToEventRepo(event: Event): EventRepo {
    val eventRepo = EventRepo()
    eventRepo.id = event.id
    eventRepo.description = event.description
    eventRepo.name = event.name
    eventRepo.dateStart = getFullDate(event.startTime, event.date)
    eventRepo.dateFinish = getFullDate(event.endTime, event.date)
    return eventRepo
}

fun convertToEvent(eventRepo: EventRepo?): Event {
    val event = Event()
    eventRepo?.let {
        event.id = eventRepo.id
        event.date = eventRepo.dateStart.toStringWithFormat(dateFormatPatternWithSlash)
        event.startTime = eventRepo.dateStart.toStringWithFormat(timeFormat)
        event.endTime = eventRepo.dateFinish.toStringWithFormat(timeFormat)
        event.name = eventRepo.name
        event.description = eventRepo.description
        }
        return event
}