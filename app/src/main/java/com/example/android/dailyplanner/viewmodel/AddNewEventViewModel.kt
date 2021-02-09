package com.example.android.dailyplanner.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.dailyplanner.entity.Event
import com.example.android.dailyplanner.entity.EventRepo
import com.example.android.dailyplanner.extensions.toDate
import com.example.android.dailyplanner.extensions.toStringWithFormat
import com.example.android.dailyplanner.interactor.Interactor
import com.example.android.dailyplanner.repository.Repository
import com.example.android.dailyplanner.utils.dateFormatPatternWithSlash
import java.util.*

class AddNewEventViewModel (val interactor: Interactor ): ViewModel() {

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
            interactor.onSave(it)
        }
       _navigationToAllDailyEvents.value = true
    }

    fun load(selectedDate: Date){
        _eventLiveData.value?.date = selectedDate.toStringWithFormat(dateFormatPatternWithSlash)
        _eventLiveData.postValue(_eventLiveData.value)
    }

}