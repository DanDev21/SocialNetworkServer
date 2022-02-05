package com.example.di.module.use_case

import com.example.use_case.activity.CreateActivity
import com.example.use_case.activity.GetActivities
import org.koin.dsl.module

val activity = module {

    single {
        CreateActivity(get(), get(), get(), get(), get())
    }

    single {
        GetActivities(get())
    }
}