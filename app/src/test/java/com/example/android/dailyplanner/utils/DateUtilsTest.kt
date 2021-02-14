package com.example.android.dailyplanner.utils

import junit.framework.TestCase
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Test
import java.util.*

class DateUtilsTest{

    @Test
    fun isTimeCorrect_startGreaterThenEnd_returnFalse(){

        val startTime = "1:00"
        val endTime = "0:30"

        val result = isTimeCorrect(startTime, endTime)

        assertThat(result, `is`(false))
    }

    @Test
    fun isTimeCorrect_startLessThenEnd_returnTrue(){

        val startTime = "0:30"
        val endTime = "1:00"

        val result = isTimeCorrect(startTime, endTime)

        assertThat(result, `is`(true))
    }

    @Test
    fun isTimeCorrect_startEqualToEnd_returnTrue(){

        val startTime = "0:30"
        val endTime = "0:30"

        val result = isTimeCorrect(startTime, endTime)

        assertThat(result, `is`(true))
    }

    @Test
    fun getFullDate_sendTimeStringDateString_returnDateTimestamp(){
        val time = "10:00"
        val date = "01/01/2021"

        val result = getFullDate(time, date)

        assertThat(result, `is`(Date(1609480800000)))
    }

}