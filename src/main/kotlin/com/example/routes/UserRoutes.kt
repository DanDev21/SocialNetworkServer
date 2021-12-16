package com.example.routes

import com.example.service.user.UserService
import com.example.domain.model.request.SignUpRequest
import com.example.domain.model.response.Response
import com.example.domain.util.AppException
import com.example.domain.util.Constants
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.signUp(userService: UserService) {
    route(Constants.Routes.User.SIGN_UP) {
        post {
            val request =
                call.receiveOrNull<SignUpRequest>()
                    ?: kotlin.run {
                        call.respond(HttpStatusCode.BadRequest)
                        return@post
                    }
            try {
                userService.add(
                    email = request.email,
                    username = request.username,
                    password = request.password
                )
                call.respond(
                    Response(
                        isSuccessful = true
                    )
                )
            } catch (e: AppException) {
                call.respond(
                    Response(
                        isSuccessful = false,
                        message = e.message
                    )
                )
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}

fun Route.signIn() {
    route(Constants.Routes.User.SIGN_IN) {

    }
}
