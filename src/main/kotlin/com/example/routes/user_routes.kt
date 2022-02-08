package com.example.routes

import com.example.domain.data.dto.jwt.JwtProperties
import com.example.domain.data.dto.request.user.SignInRequest
import com.example.domain.data.dto.request.user.SignUpRequest
import com.example.core.AppException
import com.example.domain.util.Routes
import com.example.domain.util.extensions.userId
import com.example.domain.util.extensions.receive
import com.example.domain.util.extensions.requesterId
import com.example.service.UserService
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.routing.*

fun Route.signUp(service: UserService) {
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

fun Route.findProfile(
    service: UserService
) {
    authenticate {
        get(Routes.User.FIND_PROFILE) {
            try {
                val response = service.findProfile(
                    searchedUserId = call.userId,
                    requesterId = call.requesterId
                )
                TODO()
            } catch (e: AppException) {
                TODO()
            } catch (e: Exception) {
                TODO()
            }
        }
    }
}
