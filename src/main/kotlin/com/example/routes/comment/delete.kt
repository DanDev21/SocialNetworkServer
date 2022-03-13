package com.example.routes.comment

import com.example.util.Routes
import com.example.extension.confirm
import com.example.extension.objectId
import com.example.extension.requesterId
import com.example.extension.safe
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