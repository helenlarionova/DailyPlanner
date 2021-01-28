package com.example.android.dailyplanner.ui

import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import java.util.*

class TimePickerFragment : DialogFragment() {

    private var listener: TimePickerDialog.OnTimeSetListener? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        return TimePickerDialog(requireActivity(), listener, hour, minute, true)
    }

    companion object {
        fun newInstance(listener: TimePickerDialog.OnTimeSetListener): TimePickerFragment {
            val fragment = TimePickerFragment()
            fragment.listener = listener
            return fragment
        }
    }
}