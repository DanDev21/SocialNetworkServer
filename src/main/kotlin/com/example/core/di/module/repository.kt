package com.example.core.di.module

import com.example.data.repository.activity.ActivityRepository
import com.example.data.repository.activity.ActivityRepositoryImpl
import com.example.data.repository.comment.CommentRepository
import com.example.data.repository.comment.CommentRepositoryImpl
import com.example.data.repository.follow.FollowRepository
import com.example.data.repository.follow.FollowRepositoryImpl
import com.example.data.repository.like.LikeRepository
import com.example.data.repository.like.LikeRepositoryImpl
import com.example.data.repository.post.PostRepository
import com.example.data.repository.post.PostRepositoryImpl
import com.example.data.repository.user.UserRepository
import com.example.data.repository.user.UserRepositoryImpl
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