package com.example.android.dailyplanner

import com.example.android.dailyplanner.entity.EventRepo

interface EventListCallBack {
    fun onSuccess(list: List<EventRepo>)
    fun onLoading()
    fun onError(exception: Exception)
}

interface EventCallBack {
    fun onSuccess(event: EventRepo)
    fun onLoading()
    fun onError(exception: Exception)
}