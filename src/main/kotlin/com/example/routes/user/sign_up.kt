package com.example.routes.user

import com.example.util.Routes
import com.example.extension.confirm
import com.example.extension.receive
import com.example.extension.safe
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