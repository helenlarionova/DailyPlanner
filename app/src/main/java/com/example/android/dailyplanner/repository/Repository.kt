package com.example.android.dailyplanner.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.android.dailyplanner.entity.Event
import com.google.firebase.database.*

class Repository () : IRepository {

    private val databaseReference = FirebaseDatabase.getInstance().reference
    private val query = databaseReference.child("events")
    private var eventList = arrayListOf<Event>()
    private val events = MutableLiveData<List<Event>>()

    override fun getAllDailyEvents(date : Long): MutableLiveData<List<Event>> {
        query.child(date.toString()).addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    snapshot.children.forEach { dataSnapshot ->
                        if (dataSnapshot.exists()) {
                            eventList.add(dataSnapshot?.value as Event)
                        }

                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
        events.value = eventList
        return events
    }

    override fun getEvent(eventId: Long): Event? {
        TODO("Not yet implemented")
    }

    override fun insertEvent(event: Event) {
        query.child(event.id.toString()).setValue(event)
    }

    override fun updateEvent(event: Event) {
        TODO("Not yet implemented")
    }

    override fun deleteEvent(event: Event) {
        query.child(event.id.toString()).removeValue()
    }
}