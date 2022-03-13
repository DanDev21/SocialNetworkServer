package com.example.routes.follow

import com.example.util.Routes
import com.example.extension.confirm
import com.example.extension.requesterId
import com.example.extension.safe
import com.example.extension.objectId
import com.example.domain.service.FollowService
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.routing.*

fun Route.unfollow(
    service: FollowService
) {
    authenticate {
        delete(Routes.Follow.UNFOLLOW) {
            safe {
                val result = service
                    .delete(
                        followedUserId = call.objectId,
                        followerId = call.requesterId
                    )

                call.confirm(result)
            }
        }
    }
}