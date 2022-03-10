package com.example.routes.follow

import com.example.Routes
import com.example.extensions.confirm
import com.example.extensions.requesterId
import com.example.extensions.safe
import com.example.extensions.objectId
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