package com.example.android.dailyplanner.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.dailyplanner.R
import com.example.android.dailyplanner.entity.Event
import com.example.android.dailyplanner.extensions.toStringWithFormat
import com.example.android.dailyplanner.interactor.Interactor
import com.example.android.dailyplanner.utils.dateFormatPatternWithSlash
import com.example.android.dailyplanner.utils.isTimeCorrect
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

    private var _showWarning = MutableLiveData<Int>()
    val showWarning: LiveData<Int> = _showWarning

    fun doneShowWarning(){
        _showWarning.value = null
    }

    fun checkFields(){
        val event = _eventLiveData.value
        event?.let {

            if (!checkFieldsOnEmpty(it)){
                _showWarning.value = R.string.empty_field_warning
            }else if (!checkTimeOnCorrect(it)){
                _showWarning.value = R.string.time_field_warning
            }else{
                _showWarning.value = -1
            }
        }
    }

    private fun checkFieldsOnEmpty(event: Event) : Boolean{
        return !(event.name.isEmpty() ||
                event.date.isEmpty()||
                event.startTime.isEmpty()||
                event.endTime.isEmpty()||
                event.description.isEmpty())
    }

    private fun checkTimeOnCorrect(event: Event) : Boolean{
        return isTimeCorrect(event.startTime, event.endTime)
    }

}