package com.example.android.dailyplanner.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.applandeo.materialcalendarview.EventDay
import com.example.android.dailyplanner.entity.Event
import com.example.android.dailyplanner.entity.EventRepo
import com.example.android.dailyplanner.interactor.Interactor
import com.example.android.dailyplanner.repository.EventCallBack
import com.example.android.dailyplanner.repository.Repository
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class AllDailyEventsViewModel (val interactor: Interactor) : ViewModel(), EventCallBack {

    private var _events = MutableLiveData<List<Event>>()
    val events: LiveData<List<Event>> = _events

    private var _selectedDay = MutableLiveData<String>()
    val selectedDay: LiveData<String> = _selectedDay
    val formatter = SimpleDateFormat("d MMMM yyyy", Locale.getDefault())

    init {
        val currentDate = Calendar.getInstance().time
        val currentDateString = formatter.format(currentDate)
        _selectedDay.postValue(currentDateString)
        getAllDailyEvents(currentDate)

    }

    private fun getAllDailyEvents(date: Date){
            interactor.getAllDailyEvents(date, this)
    }

    fun onDayClicked(eventDay: EventDay){
        val selected = eventDay.calendar.time
        val selectedDateString = formatter.format(selected)
        _selectedDay.value = selectedDateString
        getAllDailyEvents(selected)

    }

    private val _navigateToEventDetailFragment = MutableLiveData<String>()
    val navigateToEventDetailFragment:LiveData<String> = _navigateToEventDetailFragment

    fun onEventItemClicked(id: String){
        _navigateToEventDetailFragment.value = id
    }

    override fun onSuccess(list: List<EventRepo>) {
        _events.postValue(interactor.getListEvent(list))
    }

    override fun onError(exception: Exception) {
        TODO("Not yet implemented")
    }


}