package com.example.routes.comment

import com.example.Routes
import com.example.extensions.confirm
import com.example.extensions.objectId
import com.example.extensions.requesterId
import com.example.extensions.safe
import com.example.domain.service.CommentService
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.routing.*

fun Route.deleteComment(
    commentService: CommentService,
) {
    authenticate {
        delete(Routes.Comment.DELETE) {
            safe {
                val result = commentService
                    .delete(call.objectId, call.requesterId)

                call.confirm(result)
            }
        }
    }
}