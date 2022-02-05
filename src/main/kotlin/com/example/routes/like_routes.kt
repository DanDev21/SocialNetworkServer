package com.example.routes

import com.example.controller.post.ActivityController
import com.example.domain.data.dto.request.like.LikeRequest
import com.example.domain.data.dto.request.like.UnlikeRequest
import com.example.domain.util.AppException
import com.example.domain.util.Routes
import com.example.domain.util.extensions.receive
import com.example.domain.util.extensions.userId
import com.example.use_case.like.DeleteLikes
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.routing.*

fun Route.like(
    controller: ActivityController
) {
    authenticate {
        post(Routes.Like.CREATE) {
            try {
                val request = call.receive<LikeRequest>()
                controller.like(request, call.userId)
                // TODO: send response
            } catch (e: AppException) {
                // TODO: send response
            } catch (e: Exception) {
                // TODO: send response
            }
        }
    }
}

fun Route.unlike(
    deleteLikes: DeleteLikes
) {
    authenticate {
        delete(Routes.Like.DELETE) {
            try {
                val request = call.receive<UnlikeRequest>()
                deleteLikes(request, call.userId)
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
