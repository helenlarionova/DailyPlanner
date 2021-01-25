package com.example.android.dailyplanner.ui

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.dailyplanner.R
import com.example.android.dailyplanner.viewmodel.AddNewEventViewModel

class AddNewEventFragment : Fragment() {

    companion object {
        fun newInstance() = AddNewEventFragment()
    }

    private lateinit var viewModel: AddNewEventViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.add_new_event_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(AddNewEventViewModel::class.java)
        // TODO: Use the ViewModel
    }

}