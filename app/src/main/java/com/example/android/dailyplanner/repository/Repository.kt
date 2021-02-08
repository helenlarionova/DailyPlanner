package com.example.android.dailyplanner.repository

import com.example.android.dailyplanner.entity.EventRepo
import com.example.android.dailyplanner.extensions.atEndOfDay
import com.example.android.dailyplanner.extensions.atStartOfDay
import com.google.firebase.firestore.FirebaseFirestore
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList

interface EventListCallBack{
    fun onSuccess(list: List<EventRepo>)
    fun onError(exception: Exception)
}

interface EventCallBack{
    fun onSuccess(event: EventRepo)
    fun onError(exception: Exception)
}

class Repository (val store : FirebaseFirestore) : IRepository {

    private val db = store.collection(COLLECTION_EVENTS)


    override fun getAllDailyEvents(date: Date, callback: EventListCallBack) {

        db.whereGreaterThanOrEqualTo("startTime", date.atStartOfDay()!!)
            .get()
            .addOnSuccessListener{ task ->
                val eventList = ArrayList<EventRepo>()
                task.documents.forEach {
                    val event = it.toObject(EventRepo::class.java)!!
                    if (event.endTime <= date.atEndOfDay()) {
                        eventList.add(event)
                    }
                }
                callback.onSuccess(eventList)
            }

            .addOnFailureListener { exception ->
                callback.onError(exception)
            }

    }

    override fun getEvent(eventId: String, callback: EventCallBack) {
        db.document(eventId).get().addOnSuccessListener {document ->
            if (document.exists()){
                val event = document.toObject(EventRepo::class.java)
                event?.let { callback.onSuccess(it) }
            }

        }
    }

    override fun insertEvent(event: EventRepo) {
        val docRef = db.document()
        val eventId = docRef.id
        event.id = eventId
        docRef.set(event)
    }

    override fun updateEvent(event: EventRepo) {
        val eventRef = db.document(event.id)
        eventRef.set(event)

    }

    override fun deleteEvent(event: EventRepo) {
        val eventRef = db.document(event.id)
        eventRef.delete().addOnSuccessListener {
        }
    }

    companion object {
        const  val  COLLECTION_EVENTS  =  "events"
    }
}