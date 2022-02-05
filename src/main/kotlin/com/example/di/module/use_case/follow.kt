package com.example.di.module.use_case

import com.example.use_case.follow.FindFollows
import com.example.use_case.follow.CreateFollow
import com.example.use_case.follow.DeleteFollow
import org.koin.dsl.module

val follow = module {

    single {
        CreateFollow(get(), get())
    }

    single {
        FindFollows(get())
    }

    single {
        DeleteFollow(get())
    }
}
