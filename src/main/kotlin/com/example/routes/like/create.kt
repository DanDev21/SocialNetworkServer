package com.example.routes.like

import com.example.core.AppException
import com.example.core.util.Routes
import com.example.core.util.extensions.receive
import com.example.core.util.extensions.requesterId
import com.example.data.dto.request.like.LikeRequest
import com.example.service.ActivityService
import com.example.service.LikeService
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.routing.*

fun Route.like(
    likeService: LikeService,
    activityService: ActivityService
) {
    authenticate {
        post(Routes.Like.CREATE) {
            try {
                val request = call.receive<LikeRequest>()
                val result = likeService.add(request, call.requesterId)

                if (result.succeeded) {
                    activityService.add(request, call.requesterId)
                }
                // TODO: send response
            } catch (e: AppException) {
                // TODO: send response
            } catch (e: Exception) {
                // TODO: send response
            }
        }
    }
}