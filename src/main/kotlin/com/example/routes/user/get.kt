package com.example.routes.user

import com.example.util.Routes
import com.example.data.dto.util.DataWrapper
import com.example.extension.requesterId
import com.example.extension.safe
import com.example.extension.objectId
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

                call.respond(HttpStatusCode.OK, DataWrapper(profile))
            }
        }
    }
}