package com.example.android.dailyplanner.ui

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.os.Bundle
import android.text.format.DateUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.android.dailyplanner.R
import com.example.android.dailyplanner.databinding.AddNewEventFragmentBinding
import com.example.android.dailyplanner.extensions.toEditable
import com.example.android.dailyplanner.extensions.twoDigits
import com.example.android.dailyplanner.utils.isTimeCorrect
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

        (activity as AppCompatActivity).setSupportActionBar(_binding.toolbar)
        (activity as AppCompatActivity).getSupportActionBar()?.setDisplayHomeAsUpEnabled(true);
        (activity as AppCompatActivity).getSupportActionBar()?.setDisplayShowHomeEnabled(true);
        _binding.toolbar.setNavigationOnClickListener{
            it.findNavController().navigate(AddNewEventFragmentDirections.actionAddNewEventFragmentToAllDailyEventsFragment())
            _viewModel.doneNavigating()
            hideKeyboard()
        }

        val args = AddNewEventFragmentArgs.fromBundle(requireArguments())
        val selectedDate = args.selectedDate
        _viewModel.load(selectedDate)

        _binding.addNewEventViewModel = _viewModel

        _binding.lifecycleOwner = this

        _viewModel.navigationToAllDailyEvents.observe(viewLifecycleOwner, Observer{
            it?.let{
                this.findNavController().navigate(R.id.action_addNewEventFragment_to_allDailyEventsFragment)
                _viewModel.doneNavigating()
                hideKeyboard()


            }
        })

        _binding.fab.setOnClickListener{
            if (isFieldsOnEmpty() && isTimeOnCorrect())
                _viewModel.onSave()
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

    private fun isFieldsOnEmpty():Boolean {
        if (_binding.titleEditText.text.isNullOrBlank()||
            _binding.dateEditText.text.isNullOrEmpty() ||
            _binding.timeStartEditText.text.isNullOrEmpty()||
            _binding.timeEndEditText.text.isNullOrBlank()){
            Toast.makeText(context, getString(R.string.empty_field_warning), Toast.LENGTH_SHORT).show()
            return false
        }else{
            return true
        }
    }

    private fun isTimeOnCorrect(): Boolean {
        return if (isTimeCorrect(_binding.timeStartEditText.text.toString(), _binding.timeEndEditText.text.toString())){
            true
        }else{
            Toast.makeText(context, getString(R.string.time_field_warning), Toast.LENGTH_SHORT).show()
            false
        }
    }

    fun hideKeyboard() {
        val imm =
            requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(requireView().windowToken, 0)
    }

}