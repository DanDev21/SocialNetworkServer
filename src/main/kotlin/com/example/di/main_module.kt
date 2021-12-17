package com.example.di

import com.example.service.user.UserService
import com.example.service.user.UserServiceImpl
import com.example.domain.util.Constants
import com.example.repository.follow.FollowRepository
import com.example.repository.follow.FollowRepositoryImpl
import com.example.repository.post.PostRepository
import com.example.repository.post.PostRepositoryImpl
import com.example.repository.user.UserRepository
import com.example.repository.user.UserRepositoryImpl
import com.example.service.follow.FollowService
import com.example.service.follow.FollowServiceImpl
import com.example.service.post.PostService
import com.example.service.post.PostServiceImpl
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

    single<UserService> {
        UserServiceImpl(get())
    }

    single<FollowService> {
        FollowServiceImpl(get(), get())
    }

    single<PostService> {
        PostServiceImpl(get(), get(), get())
    }
}