package com.example.android.dailyplanner.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.applandeo.materialcalendarview.EventDay
import com.applandeo.materialcalendarview.listeners.OnDayClickListener
import com.example.android.dailyplanner.R
import com.example.android.dailyplanner.databinding.AllDailyEventsFragmentBinding
import com.example.android.dailyplanner.viewmodel.AllDailyEventsViewModel
import kotlinx.android.synthetic.main.all_daily_events_fragment.*
import java.util.*

class AllDailyEventsFragment : Fragment() {

    companion object {
        fun newInstance() = AllDailyEventsFragment()
    }

    private lateinit var viewModel: AllDailyEventsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: AllDailyEventsFragmentBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.all_daily_events_fragment,
            container,
            false
        )

        viewModel = ViewModelProviders.of(this).get(AllDailyEventsViewModel::class.java)

        binding.viewModel = viewModel

        binding.setLifecycleOwner(this)

        binding.fab.setOnClickListener { view: View ->
            Navigation.findNavController(view).navigate(R.id.action_allDailyEventsFragment2_to_addNewEventFragment2)
        }

        binding.calendarContainer.setOnDayClickListener(object : OnDayClickListener{
            override fun onDayClick(eventDay: EventDay) {
                viewModel.onDayClicked(eventDay)
            }

        })

        viewModel._selectedDay.observe(viewLifecycleOwner, androidx.lifecycle.Observer {

        })

        val adapter = EventsAdapter(
            EventListener { eventId ->
                viewModel.onEventItemClicked(eventId)
            })

        binding.recyclerView.adapter = adapter

        viewModel.navigateToEventDetailFragment.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            it?.let {
                this.findNavController().navigate(R.id.action_allDailyEventsFragment2_to_eventDetailFragment2
                )
            }

        })

        viewModel.events.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            it?.let {
                adapter.submitList(it)
            }
        })




        return binding.root
    }


}