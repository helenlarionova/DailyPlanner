package com.example.android.dailyplanner.extensions

import android.text.Editable

fun String.toEditable(): Editable =  Editable.Factory.getInstance().newEditable(this)

fun Int.twoDigits() = if (this <= 9) "0$this" else this.toString()