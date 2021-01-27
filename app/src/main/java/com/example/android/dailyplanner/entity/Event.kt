package com.example.android.dailyplanner.entity

import com.google.firebase.database.Exclude


data class Event(
    var id: String = "",
    var name:String = "",
    var description:String = "",
    var startTime: String = "",
    var endTime: String = "",
//    @Exclude
//    var date: String = ""
) {
}