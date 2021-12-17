package com.example.routes

import com.example.domain.model.request.SignInRequest
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
    post(Constants.Routes.User.SIGN_UP) {
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
                HttpStatusCode.OK,
                Response(
                    isSuccessful = true
                )
            )
        } catch (e: AppException) {
            call.respond(
                HttpStatusCode.OK,
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

fun Route.signIn(userService: UserService) {
    post(Constants.Routes.User.SIGN_IN) {
        val request =
            call.receiveOrNull<SignInRequest>()
                ?: kotlin.run {
                    call.respond(HttpStatusCode.BadRequest)
                    return@post
                }
        try {
            userService.findByCredentials(
                request.emailOrUsername,
                request.password
            )
            call.respond(
                HttpStatusCode.OK,
                Response(isSuccessful = true)
            )
        } catch (e: AppException) {
            call.respond(
                HttpStatusCode.OK,
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
