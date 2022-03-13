package com.example.routes.comment

import com.example.util.Routes
import com.example.extension.confirm
import com.example.extension.receive
import com.example.extension.requesterId
import com.example.extension.safe
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