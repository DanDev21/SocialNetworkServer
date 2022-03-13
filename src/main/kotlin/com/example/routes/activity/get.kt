package com.example.routes.activity

import com.example.util.Routes
import com.example.data.dto.util.DataWrapper
import com.example.domain.service.ActivityService
import com.example.extension.*
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
                val result = service.getUserActivities(
                    pageNumber = call.pageNumber,
                    pageSize = call.pageSize,
                    targetedUserId = call.requesterId
                )

                call.respond(HttpStatusCode.OK, DataWrapper(result.content))
            }
        }
    }
}