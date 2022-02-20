package com.example.routes.comment

import com.example.core.AppException
import com.example.core.util.Routes
import com.example.core.util.extensions.postId
import com.example.service.CommentService
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.routing.*

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