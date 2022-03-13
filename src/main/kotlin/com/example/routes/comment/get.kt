package com.example.routes.comment

import com.example.util.Routes
import com.example.data.dto.util.DataWrapper
import com.example.extension.objectId
import com.example.extension.safe
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
                val result = commentService
                    .getPostComments(call.objectId)

                call.respond(HttpStatusCode.OK, DataWrapper(result.content))
            }
        }
    }
}