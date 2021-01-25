package com.example.android.dailyplanner.ui

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.example.android.dailyplanner.R
import com.example.android.dailyplanner.databinding.AllDailyEventsFragmentBinding
import com.example.android.dailyplanner.viewmodel.AllDailyEventsViewModel
import java.text.DateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class AllDailyEventsFragment : Fragment() {

    companion object {
        fun newInstance() = AllDailyEventsFragment()
    }

    private lateinit var viewModel: AllDailyEventsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: AllDailyEventsFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.all_daily_events_fragment, container, false)

        viewModel = ViewModelProviders.of(this).get(AllDailyEventsViewModel::class.java)

        binding.viewModel = viewModel

        binding.setLifecycleOwner(this)

        binding.fab.setOnClickListener {view: View ->
            Navigation.findNavController(view).navigate(R.id.action_allDailyEventsFragment2_to_addNewEventFragment2)
        }

        return binding.root
    }


}