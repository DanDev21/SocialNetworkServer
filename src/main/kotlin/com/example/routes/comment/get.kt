package com.example.routes.comment

import com.example.Routes
import com.example.extensions.objectId
import com.example.extensions.safe
import com.example.data.dto.response.ListResponse
import com.example.domain.service.CommentService
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.getComments(
    commentService: CommentService,
) {
    authenticate {
        get(Routes.Comment.GET_POST_COMMENTS) {
            safe {
                val comments = commentService
                    .getPostComments(call.objectId)

                call.respond(HttpStatusCode.OK, ListResponse(comments))
            }
        }
    }
}