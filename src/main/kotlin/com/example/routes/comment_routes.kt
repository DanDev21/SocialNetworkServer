package com.example.routes

import com.example.controller.post.ActivityController
import com.example.controller.post.PostController
import com.example.domain.data.dto.request.comment.CommentRequest
import com.example.domain.data.dto.request.comment.DeleteCommentRequest
import com.example.domain.util.AppException
import com.example.domain.util.Routes
import com.example.domain.util.extensions.postId
import com.example.domain.util.extensions.receive
import com.example.domain.util.extensions.userId
import com.example.use_case.comment.GetComments
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.routing.*

fun Route.createComment(
    controller: ActivityController
) {
    authenticate {
        post(Routes.Comment.CREATE) {
            try {
                val request = call.receive<CommentRequest>()
                controller.comment(request, call.userId)
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
    controller: PostController
) {
    authenticate {
        delete(Routes.Comment.DELETE) {
            try {
                val request = call.receive<DeleteCommentRequest>()
                controller.deleteComment(request, call.userId)
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
    getComments: GetComments
) {
    authenticate {
        get(Routes.Comment.GET_POST_COMMENTS) {
            try {
                val comments = getComments(call.postId)
                // TODO: send response
            } catch (e: AppException) {
                // TODO: send response
            } catch (e: Exception) {
                // TODO: send response
            }
        }
    }
}
