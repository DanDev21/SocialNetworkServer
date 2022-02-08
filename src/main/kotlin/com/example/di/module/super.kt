package com.example.di.module

import com.example.controller.post.ActivityController
import com.example.controller.post.PostController
import com.example.controller.user.UserController
import org.koin.dsl.module

val extra = module {

    single {
        UserController(get(), get())
    }

    single {
        PostController(get(), get(), get(), get(), get(), get())
    }

    single {
        ActivityController(get(), get(), get(), get(), get())
    }
}