package com.example.android.dailyplanner.interfaces

import com.example.android.dailyplanner.entity.EventRepo

interface EventListCallBack {
    fun onSuccess(list: List<EventRepo>)
    fun onLoading()
    fun onError(exception: Exception)
}