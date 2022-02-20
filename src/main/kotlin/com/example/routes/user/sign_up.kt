package com.example.routes.user

import com.example.core.AppException
import com.example.data.dto.request.user.SignUpRequest
import com.example.core.util.Routes
import com.example.core.util.extensions.receive
import com.example.service.UserService
import io.ktor.application.*
import io.ktor.routing.*

fun Route.signUp(
    service: UserService
) {
    post(Routes.User.SIGN_UP) {
        try {
            val request = call.receive<SignUpRequest>()
            service.signUp(request)
            TODO()
        } catch (e: AppException) {
            TODO()
        } catch (e: Exception) {
            TODO()
        }
    }
}