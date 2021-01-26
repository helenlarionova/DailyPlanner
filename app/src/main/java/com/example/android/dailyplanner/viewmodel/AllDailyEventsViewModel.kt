package com.example.android.dailyplanner.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.applandeo.materialcalendarview.EventDay
import com.example.android.dailyplanner.entity.Event
import com.example.android.dailyplanner.repository.Repository
import java.util.*

class AllDailyEventsViewModel (val repository: Repository) : ViewModel() {

    lateinit var events : LiveData<List<Event>>

    private val today = MutableLiveData<Date>()

    init {
        today.value = Date(System.currentTimeMillis())
        TODO("инициализировать список из репозитория по дате")
        //events = getAllDailyEvents()
    }

    private fun getAllDailyEvents(date: Long) {
            repository.getAllDailyEvents(date)
    }

    private val selectedDay = MutableLiveData<Date>()
    val _selectedDay: LiveData<Date> = selectedDay

    fun onDayClicked(eventDay: EventDay){
        selectedDay.postValue(eventDay.calendar.time)
    }

    private val _navigateToEventDetailFragment = MutableLiveData<Long>()
    val navigateToEventDetailFragment:LiveData<Long> = _navigateToEventDetailFragment

    fun onEventItemClicked(id: Long){
        _navigateToEventDetailFragment.value = id
    }







}