package com.example.di

import com.example.domain.util.Constants
import com.example.repository.user.UserRepository
import com.example.repository.user.UserRepositoryImpl
import org.koin.dsl.module
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

val mainModule = module {
    single {
        KMongo.createClient().coroutine
            .getDatabase(Constants.Database.NAME)
    }

    single<UserRepository> {
        UserRepositoryImpl(get())
    }
}