package com.example.android.dailyplanner.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.android.dailyplanner.R
import com.example.android.dailyplanner.databinding.EventDetailFragmentBinding
import com.example.android.dailyplanner.viewmodel.EventDetailViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class EventDetailFragment : Fragment() {

    companion object {
        fun newInstance() = EventDetailFragment()
    }

    private val _viewModel by viewModel<EventDetailViewModel> ()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding: EventDetailFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.event_detail_fragment, container, false)

        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (activity as AppCompatActivity).getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity).getSupportActionBar()?.setDisplayShowHomeEnabled(true)
        binding.toolbar.setNavigationOnClickListener{
            it.findNavController().navigate(EventDetailFragmentDirections.actionEventDetailFragmentToAllDailyEventsFragment())
            _viewModel.doneNavigating()
        }

        val args = EventDetailFragmentArgs.fromBundle(requireArguments())
        val eventId = args.eventId
        _viewModel.load(eventId)

        binding.viewModel = _viewModel
        binding.lifecycleOwner = this

        binding.fab.setOnClickListener{
            _viewModel.onDelete()
        }

        _viewModel.navigationToAllDailyEvents.observe(viewLifecycleOwner, {
            it?.let{
                this.findNavController().navigate(R.id.action_eventDetailFragment_to_allDailyEventsFragment)
                _viewModel.doneNavigating()
            }
        })

        _viewModel.showError.observe(viewLifecycleOwner, {
            showErrorToast()
            _viewModel.doneShowErrorToast()
        })

        _viewModel.isLoading.observe(viewLifecycleOwner, {
            it?.let {
                if (it) {
                    binding.container.visibility = View.GONE
                    binding.progressBar.visibility = View.VISIBLE
                } else {
                    binding.progressBar.visibility = View.GONE
                    binding.container.visibility = View.VISIBLE
                }
            }

        })

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    private fun showErrorToast() {
        Toast.makeText(context, R.string.database_error_message, Toast.LENGTH_SHORT).show()
    }

}