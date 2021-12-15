package com.example.routes

import com.example.controller.UserController
import com.example.domain.model.request.CreateAccountRequest
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.koin.ktor.ext.inject

fun Route.userRoutes() {
    val userController: UserController by inject()
    route("/api/user/add") {
        post {
            val request = call.receiveOrNull<CreateAccountRequest>()
                ?: kotlin.run {
                    call.respond(HttpStatusCode.BadRequest)
                    return@post
                }

            try {
                userController.add(
                    email = request.email,
                    username = request.username,
                    password = request.password
                )
                println("successfully added a user")
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
