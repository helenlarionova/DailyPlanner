package com.example.android.dailyplanner.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.applandeo.materialcalendarview.EventDay
import com.applandeo.materialcalendarview.listeners.OnDayClickListener
import com.example.android.dailyplanner.R
import com.example.android.dailyplanner.databinding.AllDailyEventsFragmentBinding
import com.example.android.dailyplanner.viewmodel.AllDailyEventsViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class AllDailyEventsFragment : Fragment() {

    companion object {
        fun newInstance() = AllDailyEventsFragment()
    }

    private val _viewModel by viewModel<AllDailyEventsViewModel>()

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

        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)


        binding.viewModel = _viewModel

        binding.setLifecycleOwner(this)

        binding.fab.setOnClickListener { view: View ->
            view.findNavController().navigate(AllDailyEventsFragmentDirections.actionAllDailyEventsFragmentToAddNewEventFragment(binding.calendarContainer.firstSelectedDate.time))
            Navigation.findNavController(view).navigate(R.id.action_allDailyEventsFragment_to_addNewEventFragment)
        }


        binding.calendarContainer.setOnDayClickListener(object : OnDayClickListener{
            override fun onDayClick(eventDay: EventDay) {
                _viewModel.onDayClicked(eventDay)
            }

        })

        val adapter = EventsAdapter(
            EventListener { eventId ->
                _viewModel.onEventItemClicked(eventId)
            })

        binding.recyclerView.adapter = adapter

        _viewModel.navigateToEventDetailFragment.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            it?.let {
                this.findNavController().navigate(AllDailyEventsFragmentDirections.actionAllDailyEventsFragmentToEventDetailFragment(it))
            }

        })

        _viewModel.events.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            it?.let {
                adapter.submitList(it)

            }
        })




        return binding.root
    }


}