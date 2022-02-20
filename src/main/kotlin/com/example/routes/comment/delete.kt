package com.example.routes.comment

import com.example.core.AppException
import com.example.core.util.Routes
import com.example.core.util.extensions.receive
import com.example.core.util.extensions.requesterId
import com.example.data.dto.request.comment.DeleteCommentRequest
import com.example.service.CommentService
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.routing.*

fun Route.deleteComment(
    commentService: CommentService,
) {
    authenticate {
        delete(Routes.Comment.DELETE) {
            try {
                val request = call.receive<DeleteCommentRequest>()
                val result = commentService.delete(request, call.requesterId)
                // TODO: send response
            } catch (e: AppException) {
                // TODO: send response
            } catch (e: Exception) {
                // TODO: send response
            }
        }
    }
}