package com.example

import com.example.di.mainModule
import io.ktor.application.*
import com.example.domain.plugins.*
import org.koin.ktor.ext.Koin

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused")
fun Application.module() {
    install(Koin) {
        modules(
            mainModule
        )
    }
//    configureSecurity()
//    configureSockets()
    configureHTTP()
    configureRouting()
    configureMonitoring()
    configureSerialization()
}
