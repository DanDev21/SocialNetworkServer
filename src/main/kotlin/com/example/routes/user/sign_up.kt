package com.example.routes.user

import com.example.Routes
import com.example.extensions.confirm
import com.example.extensions.receive
import com.example.extensions.safe
import com.example.data.dto.request.user.SignupRequest
import com.example.domain.service.UserService
import io.ktor.application.*
import io.ktor.routing.*

fun Route.signUp(
    service: UserService
) {
    post(Routes.User.SIGN_UP) {
        safe {
            val request = call.receive<SignupRequest>()
            val result = service.signUp(request)

            call.confirm(result)
        }
    }
}