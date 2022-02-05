package com.example.di.module

import com.example.repository.activity.ActivityRepository
import com.example.repository.activity.ActivityRepositoryImpl
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
import org.koin.dsl.module

val repository = module {

    single<UserRepository> {
        UserRepositoryImpl(get())
    }

    single<FollowRepository> {
        FollowRepositoryImpl(get())
    }

    single<PostRepository> {
        PostRepositoryImpl(get())
    }

    single<CommentRepository> {
        CommentRepositoryImpl(get())
    }

    single<LikeRepository> {
        LikeRepositoryImpl(get())
    }

    single<ActivityRepository> {
        ActivityRepositoryImpl(get())
    }
}