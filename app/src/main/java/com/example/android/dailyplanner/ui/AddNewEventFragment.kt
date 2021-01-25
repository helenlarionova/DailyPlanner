package com.example.android.dailyplanner.ui

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.android.dailyplanner.R
import com.example.android.dailyplanner.databinding.AddNewEventFragmentBinding
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
        val binding: AddNewEventFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.add_new_event_fragment, container, false)
        viewModel = ViewModelProviders.of(this).get(AddNewEventViewModel::class.java)
        binding.addNewEventViewModel = viewModel

        binding.setLifecycleOwner(this)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


    }

}