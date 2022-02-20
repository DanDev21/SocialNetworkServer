package com.example.routes.follow

import com.example.core.AppException
import com.example.core.util.Routes
import com.example.core.util.extensions.receive
import com.example.core.util.extensions.requesterId
import com.example.data.dto.request.follow.UnfollowRequest
import com.example.service.FollowService
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.routing.*

fun Route.unfollow(
    service: FollowService
) {
    authenticate {
        delete(Routes.Follow.UNFOLLOW) {
            try {
                val request = call.receive<UnfollowRequest>()
                val result = service.delete(request, call.requesterId)
                TODO()
            } catch (e: AppException) {
                TODO()
            } catch (e: Exception) {
                TODO()
            }
        }
    }
}