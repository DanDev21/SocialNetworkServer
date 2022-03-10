package com.example.routes.follow

import com.example.Routes
import com.example.extensions.confirm
import com.example.extensions.objectId
import com.example.extensions.requesterId
import com.example.extensions.safe
import com.example.domain.service.ActivityService
import com.example.domain.service.FollowService
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.routing.*

fun Route.follow(
    followService: FollowService,
    activityService: ActivityService,
) {
    authenticate {
        post(Routes.Follow.FOLLOW_USER) {
            safe {
                val result = followService
                    .add(call.objectId, call.requesterId)

                if (result.succeeded) {
                    activityService.add(
                        followedUserId = call.objectId,
                        followerId = call.requesterId
                    )
                }
                call.confirm(result)
            }
        }
    }
}