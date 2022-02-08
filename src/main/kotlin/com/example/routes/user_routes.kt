package com.example.routes

import com.example.controller.user.UserController
import com.example.domain.data.dto.jwt.JwtProperties
import com.example.domain.data.dto.request.user.SignInRequest
import com.example.domain.data.dto.request.user.SignUpRequest
import com.example.domain.util.AppException
import com.example.domain.util.Routes
import com.example.domain.util.extensions.userId
import com.example.domain.util.extensions.receive
import com.example.domain.util.extensions.requesterId
import com.example.domain.util.extensions.username
import com.example.use_case.user.FindUsers
import com.example.use_case.user.SignIn
import com.example.use_case.user.SignUp
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.routing.*

fun Route.signUp(signUpUseCase: SignUp) {
    post(Routes.User.SIGN_UP) {
        try {
            val request = call.receive<SignUpRequest>()
            signUpUseCase(request)
            TODO()
        } catch (e: AppException) {
            TODO()
        } catch (e: Exception) {
            TODO()
        }
    }
}

fun Route.signIn(
    signIn: SignIn,
    jwtProperties: JwtProperties
) {
    get(Routes.User.SIGN_IN) {
        try {
            val request = call.receive<SignInRequest>()
            val token = signIn(
                request = request,
                jwtProperties = jwtProperties
            )
            TODO()
        } catch (e: AppException) {
            TODO()
        } catch (e: Exception) {
            TODO()
        }
    }
}

fun Route.findProfile(
    controller: UserController
) {
    authenticate {
        get(Routes.User.FIND_PROFILE) {
            try {
                val response = controller.findProfile(
                    searchableId = call.userId,
                    userId = call.requesterId
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

fun Route.findUsers(
    findUsers: FindUsers
) {
    authenticate {
        get(Routes.User.FIND) {
            try {
                val users = findUsers(
                    regex = call.username,
                    userId = call.requesterId
                ).items
                TODO()
            } catch (e: AppException) {
                TODO()
            } catch (e: Exception) {
                TODO()
            }
        }
    }
}
