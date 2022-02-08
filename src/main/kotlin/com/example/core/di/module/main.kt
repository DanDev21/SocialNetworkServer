package com.example.core.di.module

import com.example.domain.util.Database
import org.koin.dsl.module
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

val main = module {

    // kmongo

    single {
        KMongo.createClient().coroutine
            .getDatabase(Database.NAME)
    }
}