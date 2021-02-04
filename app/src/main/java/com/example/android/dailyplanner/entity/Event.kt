package com.example.android.dailyplanner.entity

data class Event (
    var id: String = "",
    var name:String = "",
    var description:String = "",
    var startTime: String = "",
    var endTime: String = "",
    var date : String = ""
)