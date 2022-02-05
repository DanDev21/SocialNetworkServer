package com.example.routes

import com.example.domain.util.AppException
import com.example.domain.util.Length
import com.example.domain.util.Routes
import com.example.domain.util.extensions.pageNumber
import com.example.domain.util.extensions.pageSize
import com.example.domain.util.extensions.userId
import com.example.use_case.activity.GetActivities
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.routing.*

fun Route.getUserActivity(
    getActivities: GetActivities
) {
    authenticate {
        get(Routes.Activity.GET_USER_ACTIVITIES) {
            try {
                val activities = getActivities(
                    pageNumber = call.pageNumber,
                    pageSize = call.pageSize ?: Length.ACTIVITY_PAGE,
                    userId = call.userId
                )
            } catch (e: AppException) {
                // TODO: send response
            } catch (e: Exception) {
                // TODO: send response
            }
        }
    }
}
