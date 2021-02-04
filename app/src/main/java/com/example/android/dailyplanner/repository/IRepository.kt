package com.example.android.dailyplanner.repository

import com.example.android.dailyplanner.entity.EventRepo
import java.util.*

interface IRepository {
    fun getAllDailyEvents(date: Date, callback: EventCallBack)
    fun getEvent(eventId: String) : EventRepo?
    fun insertEvent(event: EventRepo)
    fun updateEvent(event: EventRepo)
    fun deleteEvent(event: EventRepo)
}