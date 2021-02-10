package com.example.android.dailyplanner.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.dailyplanner.entity.Event
import com.example.android.dailyplanner.entity.EventRepo
import com.example.android.dailyplanner.extensions.toDate
import com.example.android.dailyplanner.extensions.toStringWithFormat
import com.example.android.dailyplanner.interactor.Interactor
import com.example.android.dailyplanner.repository.EventCallBack
import com.example.android.dailyplanner.repository.Repository
import com.example.android.dailyplanner.utils.dateFormatPatternWithSlash
import java.lang.Exception

class EventDetailViewModel (val interactor: Interactor) : ViewModel(){

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

    fun onDelete(){
        _eventLiveData.value?.let {
            interactor.onDelete(it)
        }
        _navigationToAllDailyEvents.value = true
    }

    private var _showError = MutableLiveData<Boolean>()
    val showError: LiveData<Boolean> = _showError

    fun doneShowErrorToast(){
        _showError.value = null
    }


    fun load(eventId: String){
        interactor.getEventById(eventId, object : EventCallBack{
            override fun onSuccess(event: EventRepo) {
                _eventLiveData.postValue(interactor.getEvent(event))
            }

            override fun onError(exception: Exception) {
                _showError.value = true
            }
        })
    }




}