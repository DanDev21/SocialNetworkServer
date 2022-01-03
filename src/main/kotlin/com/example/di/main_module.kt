package com.example.di

import com.example.controller.PostController
import com.example.domain.util.Constants
import com.example.repository.comment.CommentRepository
import com.example.repository.comment.CommentRepositoryImpl
import com.example.repository.follow.FollowRepository
import com.example.repository.follow.FollowRepositoryImpl
import com.example.repository.like.LikeRepository
import com.example.repository.like.LikeRepositoryImpl
import com.example.repository.post.PostRepository
import com.example.repository.post.PostRepositoryImpl
import com.example.repository.user.UserRepository
import com.example.repository.user.UserRepositoryImpl
import com.example.service.*
import org.koin.dsl.module
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

val mainModule = module {

    // kmongo

    single {
        KMongo.createClient().coroutine
            .getDatabase(Constants.Database.NAME)
    }

    // repository

    single<UserRepository> {
        UserRepositoryImpl(get())
    }

    single<FollowRepository> {
        FollowRepositoryImpl(get())
    }

    single<PostRepository> {
        PostRepositoryImpl(get())
    }

    single<LikeRepository> {
        LikeRepositoryImpl(get())
    }

    single<CommentRepository> {
        CommentRepositoryImpl(get())
    }

    // service

    single {
        UserService(get())
    }

    single {
        FollowService(get(), get())
    }

    single {
        PostService(get(), get())
    }

    single {
        LikeService(get(), get(), get(), get())
    }

    single {
        CommentService(get(), get(), get())
    }

    // controller

    single {
        PostController(get(), get(), get())
    }
}