package com.example.routes.post

import com.example.Routes
import com.example.extensions.confirm
import com.example.extensions.objectId
import com.example.extensions.requesterId
import com.example.extensions.safe
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