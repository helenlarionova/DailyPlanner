package com.example.android.dailyplanner.ui

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.android.dailyplanner.R
import com.example.android.dailyplanner.databinding.AddNewEventFragmentBinding
import com.example.android.dailyplanner.extensions.toEditable
import com.example.android.dailyplanner.extensions.twoDigits
import com.example.android.dailyplanner.viewmodel.AddNewEventViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class AddNewEventFragment : Fragment() {

    companion object {
        fun newInstance() = AddNewEventFragment()
    }

    private val _viewModel by viewModel<AddNewEventViewModel> ()
    private  lateinit var _binding: AddNewEventFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, R.layout.add_new_event_fragment, container, false)
        _binding.addNewEventViewModel = _viewModel

        _binding.setLifecycleOwner(this)

        _viewModel.navigationToAllDailyEvents.observe(viewLifecycleOwner, Observer{
            it?.let{
                this.findNavController().navigate(R.id.action_addNewEventFragment_to_allDailyEventsFragment)
                _viewModel.doneNavigating()


            }
        })

        _binding.fab.setOnClickListener{
            saveEvent()
        }

        _binding.dateEditText.setOnClickListener{
            showDatePickerDialog()
        }

        _binding.timeStartEditText.setOnClickListener{
            showTimePickerDialog(it)
        }

        _binding.timeEndEditText.setOnClickListener{
            showTimePickerDialog(it)
        }

        return _binding.root
    }

    private fun showTimePickerDialog(view: View) {
        val timePickerFragment = TimePickerFragment.newInstance(TimePickerDialog.OnTimeSetListener{_, hour, minute ->
            val selectedTime = "$hour:${minute.twoDigits()}"
            (view as EditText).text = selectedTime.toEditable()
        })
        timePickerFragment.show(requireActivity().supportFragmentManager, "timePicker")
    }

    private fun showDatePickerDialog() {
        val datePickerFragment = DatePickerFragment.newInstance(DatePickerDialog.OnDateSetListener{_, year, month, day ->
            val dayStr = day.twoDigits()
            val monthStr = (month + 1).twoDigits() // +1 because January is zero
            val selectedDate = "$dayStr/$monthStr/$year"
            _binding.dateEditText.text = selectedDate.toEditable()
        })
        datePickerFragment.show(requireActivity().supportFragmentManager, "datePicker")
    }

    private fun saveEvent() {
        if (_viewModel.eventLiveData.value?.name.isNullOrBlank() ||
            _viewModel.eventLiveData.value?.startTime.toString().isNullOrEmpty() ||
            _viewModel.eventLiveData.value?.endTime.toString().isNullOrEmpty()){
            Toast.makeText(context, getString(R.string.empty_field_warning), Toast.LENGTH_SHORT).show()
            return
        }else{
            _viewModel.onSave()
        }
    }

}