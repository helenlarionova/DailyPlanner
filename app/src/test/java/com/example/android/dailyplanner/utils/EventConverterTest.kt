package com.example.android.dailyplanner.utils

import com.example.android.dailyplanner.entity.Event
import com.example.android.dailyplanner.entity.EventRepo
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Test
import java.util.*

class EventConverterTest {

    @Test
    fun convertToEventRepo_sendEvent_returnEventRepo(){
        val event = Event("1", "name", "desc", "10:00", "11:00", "11/01/2021")
        val result = convertToEventRepo(event)
        assertThat(result, `is`(EventRepo("1", "name", "desc", dateStart = Date(1610344800000), dateFinish = Date(1610348400000) )))
    }

    @Test
    fun convertToEvent_sendEventRepo_returnEvent(){
        val eventRepo = EventRepo("1", "name", "desc", Date(1610344800000), Date(1610348400000))
        val result = convertToEvent(eventRepo)
        assertThat(result, `is`(Event("1", "name", "desc", "10:00", "11:00", "11/01/2021" )))
    }
}