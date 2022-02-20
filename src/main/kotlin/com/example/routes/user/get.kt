package com.example.routes.user

import com.example.core.AppException
import com.example.core.util.Routes
import com.example.core.util.extensions.requesterId
import com.example.core.util.extensions.userId
import com.example.service.UserService
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.routing.*

fun Route.getProfile(
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