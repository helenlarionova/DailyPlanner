package com.example.android.dailyplanner.entity


data class Event (
    val id:Long = 0L,
    var name:String = "",
    var description:String = "",
    var startTime: String = "",
    var endTime: String = ""
) {
}