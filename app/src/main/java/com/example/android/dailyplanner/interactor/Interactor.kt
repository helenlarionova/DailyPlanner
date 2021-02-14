package com.example.android.dailyplanner.interactor

import com.example.android.dailyplanner.entity.Event
import com.example.android.dailyplanner.entity.EventRepo
import com.example.android.dailyplanner.extensions.toStringWithFormat
import com.example.android.dailyplanner.repository.EventCallBack
import com.example.android.dailyplanner.repository.EventListCallBack
import com.example.android.dailyplanner.repository.Repository
import com.example.android.dailyplanner.utils.*
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

}


