package com.example.android.dailyplanner.viewmodel


import com.applandeo.materialcalendarview.EventDay
import com.example.android.dailyplanner.interactor.Interactor
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import java.util.*

class AllDailyEventsViewModelTest {

    private lateinit var viewModel: AllDailyEventsViewModel

    @Mock
    private lateinit var interactor: Interactor

    @Before
    fun setupViewModel() {
        MockitoAnnotations.initMocks(this)

        viewModel = AllDailyEventsViewModel(interactor)
    }

    @Test
    fun onDayClicked_setsSelectedDate() {

        val day = EventDay(Calendar.getInstance())

        viewModel.onDayClicked(day)

        val value = viewModel.selectedDate.getOrAwaitValue()

        assertThat(value, `is`(day.calendar.time))

    }

}