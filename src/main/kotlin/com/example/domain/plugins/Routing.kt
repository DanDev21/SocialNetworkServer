package com.example.domain.plugins

import com.example.routes.signIn
import com.example.service.user.UserService
import com.example.routes.signUp
import io.ktor.routing.*
import io.ktor.application.*
import org.koin.ktor.ext.inject

fun Application.configureRouting() {

    val userService: UserService by inject()

    routing {
        signUp(userService)
        signIn(userService)
    }
}
