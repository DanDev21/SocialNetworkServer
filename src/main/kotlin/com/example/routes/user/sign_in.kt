package com.example.routes.user

import com.auth0.jwt.JWT
import com.example.Routes
import com.example.extensions.intendedFor
import com.example.extensions.receive
import com.example.extensions.safe
import com.example.extensions.signUsing
import com.example.data.dto.request.user.SigninRequest
import com.example.data.dto.response.SingletonResponse
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
            val request = call.receive<SigninRequest>()
            val result = service.signIn(request)

            val token = JWT.create()
                .intendedFor(result.content)
                .signUsing(jwtProperties)

            call.respond(HttpStatusCode.OK, SingletonResponse(obj = token))
        }
    }
}