package com.example.domain.plugins

import io.ktor.routing.*
import io.ktor.http.content.*
import io.ktor.application.*
import io.ktor.response.*

fun Application.configureRouting() {


    routing {
        get("/") {
            call.respondText("Hello World!")
        }
        // Static plugin. Try to access `/static/index.html`
        static("/static") {
            resources("static")
        }
    }
}
