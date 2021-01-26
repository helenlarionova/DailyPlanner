package com.example.android.dailyplanner.entity

import com.google.firebase.database.Exclude


data class Event (
    val id:Long = 0L,
    var name:String = "",
    var description:String = "",
    var startTime: String = "",
    var endTime: String = "",
    @Exclude
    var date: Long = 0L
) {
}