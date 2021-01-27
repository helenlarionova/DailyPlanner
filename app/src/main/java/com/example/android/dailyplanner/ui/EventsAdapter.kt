package com.example.android.dailyplanner.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.dailyplanner.databinding.EventItemViewBinding
import com.example.android.dailyplanner.entity.Event

class EventsAdapter (val clickListener: EventListener) : ListAdapter<Event, EventsAdapter.ViewHolder>(
    EventDiffCallback()
){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(
            parent
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val eventItem = getItem(position)
        holder.bind(clickListener, eventItem)
    }

    class ViewHolder private constructor(val binding: EventItemViewBinding ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(clickListener: EventListener, item: Event) {
            binding.event = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = EventItemViewBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(
                    binding
                )
            }
        }

    }

}

class EventDiffCallback:
    DiffUtil.ItemCallback<Event>() {
    override fun areItemsTheSame(oldItem: Event, newItem: Event): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Event, newItem: Event): Boolean {
        return oldItem == newItem
    }
}

class EventListener(val clickListener: (eventId: String) -> Unit) {
    fun onClick(event: Event) = clickListener(event.id)
}



