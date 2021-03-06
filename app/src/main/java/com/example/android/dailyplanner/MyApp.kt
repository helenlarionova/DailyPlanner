package com.example.android.dailyplanner

import android.app.Application
import com.example.android.dailyplanner.DI.applicationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{//Start Koin
            androidLogger()
            androidContext(this@MyApp)
            modules(applicationModule)
        }
    }
}