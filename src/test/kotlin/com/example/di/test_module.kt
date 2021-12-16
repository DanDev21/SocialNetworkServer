package com.example.di

import com.example.repository.user.FakeUserRepository
import com.example.service.user.UserServiceImpl
import org.koin.dsl.module

internal val testModule = module {

    single {
        FakeUserRepository()
    }

    single {
        UserServiceImpl(get<FakeUserRepository>())
    }
}
