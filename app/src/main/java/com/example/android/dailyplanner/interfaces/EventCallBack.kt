package com.example.android.dailyplanner.interfaces

import com.example.android.dailyplanner.entity.EventRepo

interface EventCallBack {
    fun onSuccess(event: EventRepo)
    fun onLoading()
    fun onError(exception: Exception)
}