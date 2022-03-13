package com.example.routes.user

import com.example.util.Routes
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.authenticate() {
    authenticate {
        get(Routes.User.AUTHENTICATE) {
            call.respond(HttpStatusCode.OK)
        }
    }
}