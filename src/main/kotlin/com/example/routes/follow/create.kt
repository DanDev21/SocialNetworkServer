package com.example.routes.follow

import com.example.util.Routes
import com.example.extension.confirm
import com.example.extension.objectId
import com.example.extension.requesterId
import com.example.extension.safe
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