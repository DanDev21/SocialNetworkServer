package com.example.routes.like

import com.example.core.AppException
import com.example.core.util.Routes
import com.example.core.util.extensions.receive
import com.example.core.util.extensions.requesterId
import com.example.data.dto.request.like.UnlikeRequest
import com.example.service.LikeService
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.routing.*

fun Route.unlike(
    likeService: LikeService
) {
    authenticate {
        delete(Routes.Like.DELETE) {
            try {
                val request = call.receive<UnlikeRequest>()
                val result = likeService.delete(request, call.requesterId)
                // TODO: send response
            } catch (e: AppException) {
                // TODO: send response
            } catch (e: Exception) {
                // TODO: send response
                e.printStackTrace()
            }
        }
    }
}
