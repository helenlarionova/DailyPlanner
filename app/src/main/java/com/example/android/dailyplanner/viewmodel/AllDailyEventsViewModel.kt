package com.example.android.dailyplanner.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.applandeo.materialcalendarview.EventDay
import com.example.android.dailyplanner.entity.Event
import com.example.android.dailyplanner.entity.EventRepo
import com.example.android.dailyplanner.interactor.Interactor
import com.example.android.dailyplanner.repository.EventListCallBack
import com.example.android.dailyplanner.utils.dateFormatPatternFull
import com.example.android.dailyplanner.utils.localDateTimeToDate
import java.lang.Exception
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*

class AllDailyEventsViewModel (val interactor: Interactor) : ViewModel() {

    private var _events = MutableLiveData<List<Event>>()
    val events: LiveData<List<Event>> = _events

    private var _selectedDayStr = MutableLiveData<String>()
    val selectedDayStr: LiveData<String> = _selectedDayStr

    private var _selectedDate = MutableLiveData<Date>()
    val selectedDate: LiveData<Date> = _selectedDate

    private var _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData <Boolean> = _isLoading


    val formatter = SimpleDateFormat(dateFormatPatternFull, Locale.getDefault())

    init {
        _isLoading.value = true
        val currentDate = Calendar.getInstance().time
        val currentDateString = formatter.format(currentDate)
        _selectedDate.postValue(currentDate)
        _selectedDayStr.postValue(currentDateString)
        getAllDailyEvents(currentDate)
    }

    private var _showError = MutableLiveData<Boolean>()
    val showError: LiveData<Boolean> = _showError

    fun doneShowErrorToast(){
        _showError.value = null
    }

    private fun getAllDailyEvents(date: Date){
            interactor.getAllDailyEvents(date, object :EventListCallBack{
                override fun onSuccess(list: List<EventRepo>) {
                    _events.postValue(interactor.getListEvent(list))
                    _isLoading.value = false
                }

                override fun onLoading() {
                    _isLoading.value = true
                }

                override fun onError(exception: Exception) {
                    _showError.value = true
                    _isLoading.value = false
                }

            })
    }

    fun onDayClicked(day : EventDay){
        val selectedDate = day.calendar.time
        val selectedDateString = formatter.format(selectedDate)
        _selectedDate.value = selectedDate
        _selectedDayStr.value = selectedDateString
        getAllDailyEvents(selectedDate)
    }

    fun onDayClicked(dayOfMonth: Int, month: Int, year: Int){
        val selectedLocaleDate = LocalDateTime.of(year, month+1, dayOfMonth, 0, 0)
        val selectedDate = localDateTimeToDate(selectedLocaleDate)
        val selectedDateString = formatter.format(selectedDate)
        _selectedDate.value = selectedDate
        _selectedDayStr.value = selectedDateString
        getAllDailyEvents(selectedDate)
    }

    private val _navigateToEventDetailFragment = MutableLiveData<String>()
    val navigateToEventDetailFragment:LiveData<String> = _navigateToEventDetailFragment

    fun doneNavigating() {
        _navigateToEventDetailFragment.value = null
    }

    fun onEventItemClicked(id: String){
        _navigateToEventDetailFragment.value = id
    }

}