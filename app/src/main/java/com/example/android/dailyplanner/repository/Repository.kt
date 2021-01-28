package com.example.android.dailyplanner.repository

import com.example.android.dailyplanner.entity.Event
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.text.SimpleDateFormat
import java.util.*

class Repository () : IRepository {

    private val databaseReference = FirebaseDatabase.getInstance().reference
    private val query = databaseReference.child("events")


    override fun getAllDailyEvents(date: String): List<Event> {
        var eventList = arrayListOf<Event>()
        query.child(date).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    snapshot.children.forEach { dataSnapshot ->
                        if (dataSnapshot.exists()) {
                            eventList.add(dataSnapshot?.getValue(Event::class.java)!!)
                        }

                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
        return eventList
    }

    override fun getEvent(eventId: Long): Event? {
        TODO("Not yet implemented")
    }

    override fun insertEvent(event: Event) {
        val eventId = query.push().getKey()
        event.id = eventId.toString()
        val date = Date(event.startTime.toLong())
        val formatter = SimpleDateFormat("ddMMyyyy", Locale.getDefault())
        val dateStr = formatter.format(date)
        query.child(dateStr).child(event.id).setValue(event)
    }

    override fun updateEvent(event: Event) {
        TODO("Not yet implemented")
    }

    override fun deleteEvent(event: Event) {
        query.child(event.id.toString()).removeValue()
    }
}