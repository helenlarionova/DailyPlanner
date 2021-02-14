package com.example.android.dailyplanner.repository

import com.example.android.dailyplanner.entity.EventRepo
import com.example.android.dailyplanner.extensions.atEndOfDay
import com.example.android.dailyplanner.extensions.atStartOfDay
import com.example.android.dailyplanner.interfaces.EventCallBack
import com.example.android.dailyplanner.interfaces.EventListCallBack
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*
import kotlin.collections.ArrayList

class Repository(store: FirebaseFirestore) : IRepository {

    private val db = store.collection(COLLECTION_EVENTS)

    override fun getAllDailyEvents(date: Date, callback: EventListCallBack) {
        db.whereGreaterThanOrEqualTo("dateStart", date.atStartOfDay())
            .get()
            .addOnSuccessListener { task ->
                callback.onLoading()
                val eventList = ArrayList<EventRepo>()
                task.documents.forEach {
                    val event = it.toObject(EventRepo::class.java)!!
                    if (event.dateFinish <= date.atEndOfDay()) {
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
        db.document(eventId)
            .get()
            .addOnSuccessListener {document ->
                if (document.exists()){
                    callback.onLoading()
                    val event = document.toObject(EventRepo::class.java)
                    event?.let { callback.onSuccess(it)
                    }
                }
            }
            .addOnFailureListener { exception ->
                callback.onError(exception)
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
        const val COLLECTION_EVENTS = "events"
    }
}
