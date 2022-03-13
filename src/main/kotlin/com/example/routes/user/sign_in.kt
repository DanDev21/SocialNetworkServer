package com.example.routes.user

import com.auth0.jwt.JWT
import com.example.util.Routes
import com.example.data.dto.util.DataWrapper
import com.example.extension.intendedFor
import com.example.extension.receive
import com.example.extension.safe
import com.example.extension.signUsing
import com.example.data.dto.request.user.SignInRequest
import com.example.data.dto.util.JwtProperties
import com.example.domain.service.UserService
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.signIn(
    service: UserService,
    jwtProperties: JwtProperties
) {
    post(Routes.User.SIGN_IN) {
        safe {
            val request = call.receive<SignInRequest>()
            val result = service.signIn(request)

            val token = JWT.create()
                .intendedFor(result.content)
                .signUsing(jwtProperties)

            call.respond(HttpStatusCode.OK, DataWrapper(token))
        }
    }
}