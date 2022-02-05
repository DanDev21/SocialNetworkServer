package com.example.di.module.use_case

import com.example.use_case.like.CreateLike
import com.example.use_case.like.DeleteLikes
import org.koin.dsl.module

val like = module {

    single {
        CreateLike(get(), get(), get(), get())
    }

    single {
        DeleteLikes(get())
    }
}