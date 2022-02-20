package com.example.routes.user

import com.example.core.AppException
import com.example.data.dto.util.JwtProperties
import com.example.data.dto.request.user.SignInRequest
import com.example.core.util.Routes
import com.example.core.util.extensions.receive
import com.example.service.UserService
import io.ktor.application.*
import io.ktor.routing.*

fun Route.signIn(
    service: UserService,
    jwtProperties: JwtProperties
) {
    get(Routes.User.SIGN_IN) {
        try {
            val request = call.receive<SignInRequest>()
            val token = service.signIn(request, jwtProperties)
            TODO()
        } catch (e: AppException) {
            TODO()
        } catch (e: Exception) {
            TODO()
        }
    }
}