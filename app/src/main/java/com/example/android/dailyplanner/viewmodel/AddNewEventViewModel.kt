package com.example.android.dailyplanner.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.dailyplanner.entity.Event
import com.example.android.dailyplanner.repository.Repository
import kotlinx.coroutines.*

class AddNewEventViewModel (val repository : Repository): ViewModel() {
    private val _eventLiveData = MutableLiveData<Event>()
    val eventLiveData: LiveData<Event> = _eventLiveData

    init {
        _eventLiveData.value = Event()
    }

    private val _navigationToAllDailyEvents = MutableLiveData<Boolean>()
    var navigationToAllDailyEvents: LiveData<Boolean> = _navigationToAllDailyEvents

    fun doneNavigating() {
        _navigationToAllDailyEvents.value = null
    }


    fun onSave(){
        _eventLiveData.value?.let {
            repository.insertEvent(it)
        }
       _navigationToAllDailyEvents.value = true
    }



}