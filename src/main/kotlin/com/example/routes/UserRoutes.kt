package com.example.routes

import com.example.domain.data.dto.JwtProperties
import com.example.domain.data.dto.request.SignInRequest
import com.example.domain.data.dto.request.SignUpRequest
import com.example.domain.data.dto.response.Response
import com.example.domain.data.dto.response.SignInResponse
import com.example.domain.util.AppException
import com.example.domain.util.Constants
import com.example.service.UserService
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
            userService.signUp(request)
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
            call.respond(
                HttpStatusCode.OK,
                Response(isSuccessful = false)
            )
        }
    }
}

fun Route.signIn(
    userService: UserService,
    jwtProperties: JwtProperties
) {
    post(Constants.Routes.User.SIGN_IN) {
        val request =
            call.receiveOrNull<SignInRequest>()
                ?: kotlin.run {
                    call.respond(HttpStatusCode.BadRequest)
                    return@post
                }
        try {
            val token = userService.signIn(
                request = request,
                jwtProperties = jwtProperties
            )
            call.respond(
                HttpStatusCode.OK,
                SignInResponse(token = token)
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
            call.respond(
                HttpStatusCode.OK,
                Response(isSuccessful = false)
            )
        }
    }
}
