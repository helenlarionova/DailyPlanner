package com.example.android.dailyplanner.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView.ItemAnimator
import androidx.recyclerview.widget.SimpleItemAnimator
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

        setToolbar(binding)

        binding.viewModel = _viewModel

        binding.lifecycleOwner = this

        binding.fab.setOnClickListener { view: View ->
            view.findNavController().navigate(
                AllDailyEventsFragmentDirections.actionAllDailyEventsFragmentToAddNewEventFragment(
                    binding.calendarContainer.firstSelectedDate.time
                )
            )
        }

        binding.calendarContainer.setOnDayClickListener{
            _viewModel.onDayClicked(it)
        }

        val adapter = createAdapter()

        binding.recyclerView.adapter = adapter

        _viewModel.selectedDate.observe(viewLifecycleOwner, {
            it?.let {
                binding.calendarContainer.setDate(it)
            }
        })

        _viewModel.navigateToEventDetailFragment.observe(viewLifecycleOwner, {
            it?.let {
                this.findNavController().navigate(
                    AllDailyEventsFragmentDirections.actionAllDailyEventsFragmentToEventDetailFragment(
                        it
                    )
                )
                _viewModel.doneNavigating()
            }
        })

        _viewModel.events.observe(viewLifecycleOwner, {
            it?.let {
                adapter.events = ArrayList(it)
            }
        })

        _viewModel.showError.observe(viewLifecycleOwner, {
            showErrorToast()
            _viewModel.doneShowErrorToast()
        })

        _viewModel.isLoading.observe(viewLifecycleOwner, {
            it?.let {
                if (it) {
                    binding.recyclerView.visibility = View.GONE
                    binding.progressBar.visibility = View.VISIBLE
                } else {
                    binding.progressBar.visibility = View.GONE
                    binding.recyclerView.visibility = View.VISIBLE
                }
            }

        })

        return binding.root
    }

    private fun setToolbar(binding: AllDailyEventsFragmentBinding) {
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
    }

    private fun createAdapter(): EventsAdapter {
        val adapter = EventsAdapter(
            EventListener { eventId ->
                _viewModel.onEventItemClicked(eventId)
            })
        return adapter
    }

    private fun showErrorToast() {
        Toast.makeText(context, R.string.database_error_message, Toast.LENGTH_SHORT).show()
    }

}