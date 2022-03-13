package com.example.routes.like

import com.example.util.Routes
import com.example.extension.confirm
import com.example.extension.objectId
import com.example.extension.requesterId
import com.example.extension.safe
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
