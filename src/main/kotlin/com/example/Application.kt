package com.example

import com.example.di.module.main
import com.example.di.module.extra
import com.example.di.module.repository
import com.example.di.module.use_case.*
import io.ktor.application.*
import com.example.plugins.*
import org.koin.ktor.ext.Koin

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused")
fun Application.module() {
    install(Koin) {
        modules(
            main,
            repository,
            user,
            follow,
            post,
            comment,
            like,
            activity,
            extra
        )
    }
    configureSecurity()
    configureSockets()
    configureHTTP()
    configureRouting()
    configureMonitoring()
    configureSerialization()
}
