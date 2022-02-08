package com.example.di.module.use_case

import com.example.use_case.post.CreatePost
import com.example.use_case.post.DeletePost
import com.example.use_case.post.FindPosts
import org.koin.dsl.module

val post = module {

    single {
        CreatePost(get(), get())
    }

    single {
        FindPosts(get())
    }

    single {
        DeletePost(get())
    }
}
