package com.example.di

import com.example.repository.user.FakeUserRepository
import com.example.service.UserService
import org.koin.dsl.module

internal val testModule = module {

    single {
        FakeUserRepository()
    }

    single {
        UserService(get<FakeUserRepository>())
    }
}
