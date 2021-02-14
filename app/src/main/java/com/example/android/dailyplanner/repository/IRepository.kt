package com.example.android.dailyplanner.repository

import com.example.android.dailyplanner.entity.EventRepo
import com.example.android.dailyplanner.interfaces.EventCallBack
import com.example.android.dailyplanner.interfaces.EventListCallBack
import java.util.*

interface IRepository {
    fun getAllDailyEvents(date: Date, callback: EventListCallBack)
    fun getEvent(eventId: String, callback: EventCallBack)
    fun insertEvent(event: EventRepo)
    fun deleteEvent(event: EventRepo)
}