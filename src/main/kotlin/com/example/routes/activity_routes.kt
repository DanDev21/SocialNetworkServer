package com.example.routes

import com.example.core.AppException
import com.example.domain.util.Length
import com.example.domain.util.Routes
import com.example.domain.util.extensions.pageNumber
import com.example.domain.util.extensions.pageSize
import com.example.domain.util.extensions.requesterId
import com.example.service.ActivityService
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.routing.*

fun Route.getUserActivities(
    service: ActivityService
) {
    authenticate {
        get(Routes.Activity.GET_USER_ACTIVITIES) {
            try {
                val result = service.getUserActivities(
                    pageNumber = call.pageNumber,
                    pageSize = call.pageSize ?: Length.ACTIVITY_PAGE,
                    targetedUserId = call.requesterId
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
