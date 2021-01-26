package com.example.android.dailyplanner.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.android.dailyplanner.R
import com.example.android.dailyplanner.databinding.AddNewEventFragmentBinding
import com.example.android.dailyplanner.viewmodel.AddNewEventViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class AddNewEventFragment : Fragment() {

    companion object {
        fun newInstance() = AddNewEventFragment()
    }

    private val viewModel by viewModel<AddNewEventViewModel> ()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: AddNewEventFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.add_new_event_fragment, container, false)
        binding.addNewEventViewModel = viewModel

        binding.setLifecycleOwner(this)

        viewModel.navigationToAllDailyEvents.observe(viewLifecycleOwner, Observer{
            if (it){
                this.findNavController().navigate(R.id.action_addNewEventFragment_to_allDailyEventsFragment)
                viewModel.doneNavigating()


            }
        })

        binding.fab.setOnClickListener{
            saveEvent()
        }

        return binding.root
    }

    private fun saveEvent() {
        if (viewModel.eventLiveData.value?.name.isNullOrBlank() ||
            viewModel.eventLiveData.value?.startTime.isNullOrBlank() ||
            viewModel.eventLiveData.value?.endTime.isNullOrBlank()){
            Toast.makeText(context, getString(R.string.empty_field_warning), Toast.LENGTH_SHORT).show()
            return
        }else{
            viewModel.onSave()
        }
    }

}