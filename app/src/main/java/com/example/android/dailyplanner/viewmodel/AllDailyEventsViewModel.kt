package com.example.android.dailyplanner.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

class AllDailyEventsViewModel : ViewModel() {

    private val today = MutableLiveData<Date>()
    val _today: LiveData<Date> = today




}