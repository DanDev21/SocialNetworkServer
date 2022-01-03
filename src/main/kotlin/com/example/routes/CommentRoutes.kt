package com.example.routes

import com.example.controller.PostController
import com.example.domain.data.dto.request.CommentRequest
import com.example.domain.data.dto.request.DeleteCommentRequest
import com.example.domain.util.AppException
import com.example.domain.util.Constants
import com.example.service.CommentService
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.createComment(
    commentService: CommentService
) {
    authenticate {
        post(Constants.Routes.Comment.CREATE) {
            val request = call.receiveOrNull<CommentRequest>() ?: kotlin.run {
                call.respond(HttpStatusCode.BadRequest)
                return@post
            }
            try {
                commentService.add(call.userId, request)
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
    postController: PostController
) {
    authenticate {
        delete(Constants.Routes.Comment.DELETE) {
            val request = call.receiveOrNull<DeleteCommentRequest>() ?: kotlin.run {
                call.respond(HttpStatusCode.BadRequest)
                return@delete
            }
            try {
                postController
                    .deleteComment(call.userId, request)
                // TODO: send response
            } catch (e: AppException) {
                // TODO: send response
            } catch (e: Exception) {
                // TODO: send response
            }
        }
    }
}

fun Route.getPostsComments(
    commentService: CommentService
) {
    authenticate {
        get(Constants.Routes.Comment.GET_POST_COMMENTS) {
            val postId = call.parameters[Constants.RequestParam.POST_ID]
                ?: ""
            try {
                val comments = commentService
                    .getAll(postId)
                // TODO: send response
            } catch (e: AppException) {
                // TODO: send response
            } catch (e: Exception) {
                // TODO: send response
            }
        }
    }
}
