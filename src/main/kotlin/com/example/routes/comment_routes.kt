package com.example.routes

import com.example.domain.data.dto.request.comment.CommentRequest
import com.example.domain.data.dto.request.comment.DeleteCommentRequest
import com.example.core.AppException
import com.example.domain.util.Routes
import com.example.domain.util.extensions.postId
import com.example.domain.util.extensions.receive
import com.example.domain.util.extensions.requesterId
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

fun Route.getComments(
    commentService: CommentService,
) {
    authenticate {
        get(Routes.Comment.GET_POST_COMMENTS) {
            try {
                val comments = commentService.getPostComments(call.postId)
                // TODO: send response
            } catch (e: AppException) {
                // TODO: send response
            } catch (e: Exception) {
                // TODO: send response
            }
        }
    }
}
