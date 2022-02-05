package com.example.di.module.use_case

import com.example.use_case.user.FindUsers
import com.example.use_case.user.SignIn
import com.example.use_case.user.SignUp
import org.koin.dsl.module

val user = module {

    single {
        SignUp(get())
    }

    single {
        SignIn(get())
    }

    single {
        FindUsers(get())
    }
}
