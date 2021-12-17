package com.example.plugins

import io.ktor.gson.*
import io.ktor.features.*
import io.ktor.application.*
import io.ktor.routing.*

fun Application.configureSerialization() {
    install(ContentNegotiation) {
        gson {
        }
    }
}
