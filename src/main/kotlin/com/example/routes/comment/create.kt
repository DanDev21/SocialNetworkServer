package com.example.routes.comment

import com.example.core.AppException
import com.example.core.util.Routes
import com.example.core.util.extensions.receive
import com.example.core.util.extensions.requesterId
import com.example.data.dto.request.comment.CommentRequest
import com.example.service.ActivityService
import com.example.service.CommentService
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.routing.*

fun Route.createComment(
    commentService: CommentService,
    activityService: ActivityService,
) {
    authenticate {
        post(Routes.Comment.CREATE) {
            try {
                val request = call.receive<CommentRequest>()
                val result = commentService.add(request, call.requesterId)

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