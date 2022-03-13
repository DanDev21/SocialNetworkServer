package com.example.plugins

import com.example.util.Database
import com.example.domain.repository.*
import com.example.domain.service.*
import com.google.gson.Gson
import org.koin.dsl.module
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

val main = module {

    // kmongo
    single {
        KMongo.createClient().coroutine
            .getDatabase(Database.NAME)
    }

    // gson
    single { Gson() }
}

val repository = module {

    single<com.example.data.repository.UserRepository> {
        UserRepository(get())
    }

    single<com.example.data.repository.FollowRepository> {
        FollowRepository(get())
    }

    single<com.example.data.repository.PostRepository> {
        PostRepository(get())
    }

    single<com.example.data.repository.CommentRepository> {
        CommentRepository(get())
    }

    single<com.example.data.repository.LikeRepository> {
        LikeRepository(get())
    }

    single<com.example.data.repository.ActivityRepository> {
        ActivityRepository(get())
    }
}

val service = module {

    single { UserService(get(), get()) }

    single { FollowService(get(), get()) }

    single {
        PostService(get(), get(), get(), get())
    }

    single {
        CommentService(get(), get(), get(), get())
    }

    single {
        LikeService(get(), get(), get(), get())
    }

    single {
        ActivityService(get(), get(), get(), get())
    }
}