package com.example.android.dailyplanner.entity


data class Event (
    val id:Long,
    val name:String,
    val description:String,
    val startTime: Long,
    val endTime: Long
) {
}