package com.example

import com.example.plugins.*
import io.ktor.application.*
import org.koin.ktor.ext.Koin

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused")
fun Application.module() {
    install(Koin) {
        modules(
            main,
            repository,
            service
        )
    }
    configureSecurity()
    configureSockets()
    configureHTTP()
    configureRouting()
    configureMonitoring()
    configureSerialization()
}
