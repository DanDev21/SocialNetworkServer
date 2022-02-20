package com.example.routes.follow

import com.example.core.AppException
import com.example.core.util.Routes
import com.example.core.util.extensions.receive
import com.example.core.util.extensions.requesterId
import com.example.data.dto.request.follow.FollowRequest
import com.example.service.ActivityService
import com.example.service.FollowService
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.routing.*

fun Route.follow(
    followService: FollowService,
    activityService: ActivityService,
) {
    authenticate {
        post(Routes.Follow.FOLLOW_USER) {
            try {
                val request = call.receive<FollowRequest>()
                val result = followService.add(request, call.requesterId)

                if (result.succeeded) {
                    activityService.add(request, call.requesterId)
                }
                TODO()
            } catch (e: AppException) {
                TODO()
            } catch (e: Exception) {
                TODO()
            }
        }
    }
}