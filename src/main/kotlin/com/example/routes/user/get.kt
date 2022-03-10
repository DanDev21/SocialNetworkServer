package com.example.routes.user

import com.example.Routes
import com.example.extensions.requesterId
import com.example.extensions.safe
import com.example.extensions.objectId
import com.example.data.dto.response.SingletonResponse
import com.example.domain.service.UserService
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.getProfile(
    service: UserService
) {
    authenticate {
        get(Routes.User.GET_PROFILE) {
            safe {
                val profile = service.find(
                    searchedUserId = call.objectId,
                    requesterId = call.requesterId
                )

                call.respond(HttpStatusCode.OK, SingletonResponse(profile))
            }
        }
    }
}