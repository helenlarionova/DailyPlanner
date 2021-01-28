package com.example.android.dailyplanner.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.applandeo.materialcalendarview.EventDay
import com.example.android.dailyplanner.entity.Event
import com.example.android.dailyplanner.repository.Repository
import java.text.SimpleDateFormat
import java.util.*

class AllDailyEventsViewModel (val repository: Repository) : ViewModel() {

    private var _events = MutableLiveData<List<Event>>()
    val events: LiveData<List<Event>> = _events

    private var _selectedDay = MutableLiveData<String>()
    val selectedDay: LiveData<String> = _selectedDay
    val formatter = SimpleDateFormat("ddMMyyyy", Locale.getDefault())

    init {
        val currentDate = formatter.format(Calendar.getInstance().time)
        _selectedDay.postValue(currentDate)
        _events.postValue(getAllDailyEvents(currentDate))

    }

    private fun getAllDailyEvents(date: String) : List<Event> {
            return repository.getAllDailyEvents(date)
    }

    fun onDayClicked(eventDay: EventDay){
        val selected = eventDay.calendar.time
        val selectedDate = formatter.format(selected)
        _selectedDay.value = selectedDate
        _events.value = getAllDailyEvents(selectedDate)
    }

    private val _navigateToEventDetailFragment = MutableLiveData<String>()
    val navigateToEventDetailFragment:LiveData<String> = _navigateToEventDetailFragment

    fun onEventItemClicked(id: String){
        _navigateToEventDetailFragment.value = id
    }







}