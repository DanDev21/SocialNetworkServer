package com.example.di.module.use_case

import com.example.use_case.comment.CreateComment
import com.example.use_case.comment.DeleteComments
import com.example.use_case.comment.FindComment
import com.example.use_case.comment.GetComments
import org.koin.dsl.module

val comment = module {

    single {
        CreateComment(get(), get(), get())
    }

    single {
        FindComment(get())
    }

    single {
        DeleteComments(get())
    }

    single {
        GetComments(get())
    }
}
