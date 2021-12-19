package com.example.di

import com.example.service.UserService
import com.example.domain.util.Constants
import com.example.repository.follow.FollowRepository
import com.example.repository.follow.FollowRepositoryImpl
import com.example.repository.post.PostRepository
import com.example.repository.post.PostRepositoryImpl
import com.example.repository.user.UserRepository
import com.example.repository.user.UserRepositoryImpl
import com.example.service.FollowService
import com.example.service.PostService
import org.koin.dsl.module
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

val mainModule = module {
    single {
        KMongo.createClient().coroutine
            .getDatabase(Constants.Database.NAME)
    }

    // repos

    single<UserRepository> {
        UserRepositoryImpl(get())
    }

    single<FollowRepository> {
        FollowRepositoryImpl(get())
    }

    single<PostRepository> {
        PostRepositoryImpl(get())
    }

    // services

    single {
        UserService(get())
    }

    single {
        FollowService(get(), get())
    }

    single {
        PostService(get(), get(), get())
    }
}