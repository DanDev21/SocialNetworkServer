package com.example.routes.like

import com.example.util.Routes
import com.example.extension.confirm
import com.example.extension.receive
import com.example.extension.requesterId
import com.example.extension.safe
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