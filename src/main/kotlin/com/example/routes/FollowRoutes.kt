package com.example.routes

import com.example.domain.data.dto.request.FollowRequest
import com.example.domain.data.dto.request.UnfollowRequest
import com.example.domain.data.dto.response.Response
import com.example.domain.util.AppException
import com.example.domain.util.Constants
import com.example.service.FollowService
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.follow(followService: FollowService) {
    post(Constants.Routes.Follow.NEW) {
        val request = call.receiveOrNull<FollowRequest>() ?: kotlin.run {
            call.respond(HttpStatusCode.BadRequest)
            return@post
        }
        try {
            followService.add(
                byWhoId = request.byWhoId,
                otherId = request.otherId
            )
            call.respond(
                HttpStatusCode.OK,
                Response(isSuccessful = true)
            )
        } catch (e: AppException) {
            call.respond(
                HttpStatusCode.OK,
                Response(
                    isSuccessful = false,
                    message = e.message
                )
            )
        } catch (e: Exception) {
            e.printStackTrace()
            call.respond(
                HttpStatusCode.OK,
                Response(isSuccessful = false)
            )
        }
    }
}

fun Route.unfollow(followService: FollowService) {
    delete(Constants.Routes.Follow.UNFOLLOW) {
        val request = call.receiveOrNull<UnfollowRequest>() ?: kotlin.run {
            call.respond(HttpStatusCode.BadRequest)
            return@delete
        }

        try {
            call.respond(
                HttpStatusCode.OK,
                Response(
                    isSuccessful = followService.delete(
                        byWhoId = request.byWhoId,
                        otherId = request.otherId
                    )
                )
            )
        } catch (e: AppException) {
            call.respond(
                HttpStatusCode.OK,
                Response(
                    isSuccessful = false,
                    message = e.message
                )
            )
        } catch (e: Exception) {
            e.printStackTrace()
            call.respond(
                HttpStatusCode.OK,
                Response(isSuccessful = false)
            )
        }
    }
}
