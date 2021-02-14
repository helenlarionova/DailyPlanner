package com.example.android.dailyplanner.entity

import android.os.health.TimerStat
import com.google.firebase.Timestamp
import java.util.*


data class EventRepo(
    var id: String = "",
    var name:String = "",
    var description:String = "",
    var dateStart: Date = Date(),
    var dateFinish: Date = Date(),
)