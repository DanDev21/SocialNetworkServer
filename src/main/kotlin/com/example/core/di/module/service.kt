package com.example.core.di.module

import com.example.service.*
import org.koin.dsl.module

val service = module {

    single {
        UserService()
    }

    single {
        FollowService(get())
    }

    single {
        PostService(get())
    }

    single {
        CommentService(get(), get())
    }

    single {
        LikeService(get(), get(), get())
    }

    single {
        ActivityService(get(), get())
    }
}