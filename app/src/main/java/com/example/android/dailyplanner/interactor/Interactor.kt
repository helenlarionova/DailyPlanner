package com.example.android.dailyplanner.interactor

import com.example.android.dailyplanner.entity.Event
import com.example.android.dailyplanner.entity.EventRepo
import com.example.android.dailyplanner.extensions.toStringWithFormat
import com.example.android.dailyplanner.repository.EventCallBack
import com.example.android.dailyplanner.repository.EventListCallBack
import com.example.android.dailyplanner.repository.Repository
import com.example.android.dailyplanner.utils.dateFormatPatternWithSlash
import com.example.android.dailyplanner.utils.timeFormat
import java.text.SimpleDateFormat
import java.util.*

class Interactor(val repository : Repository){

    fun onSave(event: Event){
      repository.insertEvent(convertToEventRepo(event))
    }

    fun getAllDailyEvents(date: Date, callBack: EventListCallBack){
        repository.getAllDailyEvents(date, callBack)
    }

    fun getListEvent(list: List<EventRepo>): List<Event> = list.map {
            convertToEvent(it)
        }

    fun getEventById(id: String, callBack: EventCallBack) = repository.getEvent(id, callBack)

    fun getEvent(eventRepo: EventRepo) : Event = convertToEvent(eventRepo)

    fun onDelete(event: Event){
        repository.deleteEvent(convertToEventRepo(event))
    }


    private fun convertToEventRepo(event: Event): EventRepo {
        val eventRepo = EventRepo()
        eventRepo.id = event.id
        eventRepo.description = event.description
        eventRepo.name = event.name
        eventRepo.startTime = getFullDate(event.startTime, event.date)
        eventRepo.endTime = getFullDate(event.endTime, event.date)
        return eventRepo


    }

    private fun convertToEvent(eventRepo: EventRepo?): Event{
        val event = Event()
        eventRepo?.let {
            event.id = eventRepo.id
            event.date = eventRepo.startTime.toStringWithFormat(dateFormatPatternWithSlash)
            event.startTime = eventRepo.startTime.toStringWithFormat(timeFormat)
            event.endTime = eventRepo.endTime.toStringWithFormat(timeFormat)
            event.name = eventRepo.name
            event.description = eventRepo.description
        }

        return event
    }

    private fun getFullDate(time: String, date: String): Date {
        val formatter = SimpleDateFormat("$dateFormatPatternWithSlash $timeFormat", Locale.getDefault())
        val dateInString = "$date $time"
        return formatter.parse(dateInString)!!
    }
}


