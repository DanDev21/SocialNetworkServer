package com.example.routes.like

import com.example.Routes
import com.example.extensions.confirm
import com.example.extensions.objectId
import com.example.extensions.requesterId
import com.example.extensions.safe
import com.example.domain.service.LikeService
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.routing.*

fun Route.unlike(
    likeService: LikeService
) {
    authenticate {
        delete(Routes.Like.DELETE) {
            safe {
                val result = likeService
                    .delete(call.objectId, call.requesterId)

                call.confirm(result)
            }
        }
    }
}
