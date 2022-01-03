package com.example.routes

import com.example.domain.data.dto.request.LikeRequest
import com.example.domain.data.dto.request.UnlikeRequest
import com.example.domain.util.AppException
import com.example.domain.util.Constants
import com.example.service.LikeService
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.like(
    likeService: LikeService
) {
    authenticate {
        post(Constants.Routes.Like.CREATE) {
            val request = call.receiveOrNull<LikeRequest>() ?: kotlin.run {
                call.respond(HttpStatusCode.BadRequest)
                return@post
            }

            try {
                likeService.add(call.userId, request)
                // TODO: send response
            } catch (e: AppException) {
                // TODO: send response
            } catch (e: Exception) {
                // TODO: send response
                e.printStackTrace()
            }
        }
    }
}

fun Route.unlike(
    likeService: LikeService
) {
    authenticate {
        delete(Constants.Routes.Like.DELETE) {
            val request = call.receiveOrNull<UnlikeRequest>() ?: kotlin.run {
                call.respond(HttpStatusCode.BadRequest)
                return@delete
            }
            try {
                likeService.delete(call.userId, request)
                // TODO: send response
            } catch (e: AppException) {
                // TODO: send response
            } catch (e: Exception) {
                // TODO: send response
                e.printStackTrace()
            }
        }
    }
}
