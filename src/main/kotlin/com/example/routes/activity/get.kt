package com.example.routes.activity

import com.example.Routes
import com.example.extensions.receive
import com.example.extensions.requesterId
import com.example.data.dto.request.PaginatedResourceRequest
import com.example.data.dto.response.ListResponse
import com.example.domain.service.ActivityService
import com.example.extensions.safe
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.getUserActivities(
    service: ActivityService
) {
    authenticate {
        get(Routes.Activity.GET_USER_ACTIVITIES) {
            safe {
                val request = call.receive<PaginatedResourceRequest>()
                val result = service.getUserActivities(
                    request = request,
                    targetedUserId = call.requesterId
                )

                call.respond(HttpStatusCode.OK, ListResponse(result))
            }
        }
    }
}