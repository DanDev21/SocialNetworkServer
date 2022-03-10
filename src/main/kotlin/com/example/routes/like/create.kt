package com.example.routes.like

import com.example.Routes
import com.example.extensions.confirm
import com.example.extensions.receive
import com.example.extensions.requesterId
import com.example.extensions.safe
import com.example.data.dto.request.like.LikeRequest
import com.example.domain.service.ActivityService
import com.example.domain.service.LikeService
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.routing.*

fun Route.like(
    likeService: LikeService,
    activityService: ActivityService
) {
    authenticate {
        post(Routes.Like.CREATE) {
            safe {
                val request = call.receive<LikeRequest>()
                val result = likeService.add(request, call.requesterId)

                if (result.succeeded) {
                    activityService.add(request, call.requesterId)
                }
                call.confirm(result)
            }
        }
    }
}