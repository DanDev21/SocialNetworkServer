package com.example.routes

import com.example.domain.data.dto.request.FollowRequest
import com.example.domain.data.dto.request.UnfollowRequest
import com.example.domain.data.dto.response.Response
import com.example.domain.util.AppException
import com.example.service.FollowService
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.follow(followService: FollowService) {
    authenticate {
        post(Routes.Follow.FOLLOW_USER) {
            val request = call.receiveOrNull<FollowRequest>() ?: kotlin.run {
                call.respond(HttpStatusCode.BadRequest)
                return@post
            }
            try {
                followService.add(
                    byWhoId = call.userId,
                    otherId = request.otherId
                )
                call.respond(
                    HttpStatusCode.OK,
                    Response(isSuccessful = true)
                )
            } catch (e: AppException) {

            } catch (e: Exception) {

            }
        }
    }
}

fun Route.unfollow(followService: FollowService) {
    authenticate {
        delete(Routes.Follow.UNFOLLOW) {
            val request = call.receiveOrNull<UnfollowRequest>() ?: kotlin.run {
                call.respond(HttpStatusCode.BadRequest)
                return@delete
            }

            try {
                call.respond(
                    HttpStatusCode.OK,
                    Response(
                        isSuccessful = followService
                            .delete(call.userId, request)
                    )
                )
            } catch (e: AppException) {

            } catch (e: Exception) {

            }
        }
    }
}
