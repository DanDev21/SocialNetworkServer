package com.example.di.module.use_case

import com.example.use_case.comment.CreateComment
import com.example.use_case.comment.DeleteComments
import com.example.use_case.comment.FindComments
import org.koin.dsl.module

val comment = module {

    single {
        CreateComment(get(), get(), get())
    }

    single {
        FindComments(get())
    }

    single {
        DeleteComments(get())
    }
}
