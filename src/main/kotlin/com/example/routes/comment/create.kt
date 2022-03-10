package com.example.routes.comment

import com.example.Routes
import com.example.extensions.confirm
import com.example.extensions.receive
import com.example.extensions.requesterId
import com.example.extensions.safe
import com.example.data.dto.request.comment.CommentRequest
import com.example.domain.service.ActivityService
import com.example.domain.service.CommentService
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.routing.*

fun Route.createComment(
    commentService: CommentService,
    activityService: ActivityService,
) {
    authenticate {
        post(Routes.Comment.CREATE) {
            safe {
                val request = call.receive<CommentRequest>()
                val result = commentService.add(request, call.requesterId)

                if (result.succeeded) {
                    activityService.add(request, call.requesterId)
                }
                call.confirm(result)
            }
        }
    }
}