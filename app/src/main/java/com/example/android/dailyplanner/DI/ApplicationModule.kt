package com.example.android.dailyplanner.DI

import com.example.android.dailyplanner.repository.Repository
import com.example.android.dailyplanner.viewmodel.AddNewEventViewModel
import com.example.android.dailyplanner.viewmodel.AllDailyEventsViewModel
import com.example.android.dailyplanner.viewmodel.EventDetailViewModel
import com.google.firebase.database.core.Repo
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

 val applicationModule: Module = module {

     single <Repository> {
         Repository()
     }

     viewModel {
         AllDailyEventsViewModel(get())
     }

     viewModel {
         AddNewEventViewModel(get())
     }

     viewModel {
         EventDetailViewModel(get())
     }

 }

