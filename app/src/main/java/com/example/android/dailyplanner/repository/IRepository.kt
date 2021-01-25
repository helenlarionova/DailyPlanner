package com.example.android.dailyplanner.repository

import androidx.lifecycle.LiveData
import com.example.android.dailyplanner.entity.Event

interface IRepository {
    fun getAllDailyEvents(date : Long): LiveData<List<Event>>
    fun getEvent(eventId: Long) : Event?
    fun insertEvent(event: Event)
    fun updateEvent(event: Event)
    fun deleteEvent(event: Event)
}