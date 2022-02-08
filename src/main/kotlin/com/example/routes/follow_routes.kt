package com.example.routes

import com.example.domain.data.dto.request.follow.FollowRequest
import com.example.domain.data.dto.request.follow.UnfollowRequest
import com.example.domain.util.AppException
import com.example.domain.util.Routes
import com.example.domain.util.extensions.receive
import com.example.domain.util.extensions.requesterId
import com.example.use_case.follow.CreateFollow
import com.example.use_case.follow.DeleteFollow
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.routing.*

fun Route.follow(
    createFollow: CreateFollow
) {
    authenticate {
        post(Routes.Follow.FOLLOW_USER) {
            try {
                val request = call.receive<FollowRequest>()
                createFollow(request, call.requesterId)
            } catch (e: AppException) {
                TODO()
            } catch (e: Exception) {
                TODO()
            }
        }
    }
}

fun Route.unfollow(deleteFollow: DeleteFollow) {
    authenticate {
        delete(Routes.Follow.UNFOLLOW) {
            try {
                val request = call.receive<UnfollowRequest>()
                deleteFollow(request, call.requesterId)
            } catch (e: AppException) {
                TODO()
            } catch (e: Exception) {
                TODO()
            }
        }
    }
}
