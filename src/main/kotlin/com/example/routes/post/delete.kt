package com.example.routes.post

import com.example.util.Routes
import com.example.extension.confirm
import com.example.extension.objectId
import com.example.extension.requesterId
import com.example.extension.safe
import com.example.domain.service.PostService
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.routing.*

fun Route.deletePost(
    service: PostService
) {
    authenticate {
        delete(Routes.Post.DELETE_POST) {
            safe {
                val result = service
                    .delete(call.objectId, call.requesterId)

                call.confirm(result)
            }
        }
    }
}