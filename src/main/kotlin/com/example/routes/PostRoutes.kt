package com.example.routes

import com.example.controller.PostController
import com.example.domain.data.dto.request.CreatePostRequest
import com.example.domain.data.dto.request.DeletePostRequest
import com.example.domain.data.dto.response.Response
import com.example.domain.util.AppException
import com.example.domain.util.Constants
import com.example.domain.util.Constants.RequestParam
import com.example.service.FollowService
import com.example.service.PostService
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.createPost(
    postService: PostService,
) {
    authenticate {
        post(Routes.Post.CREATE_POST) {
            val request = call.receiveOrNull<CreatePostRequest>() ?: kotlin.run {
                call.respond(HttpStatusCode.BadRequest)
                return@post
            }
            try {
                postService.add(call.userId, request)
            } catch (e: AppException) {
                call.respond(
                    HttpStatusCode.OK,
                    Response(
                        isSuccessful = false,
                        e.message
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
}

fun Route.deletePost(
    postController: PostController
) {
    authenticate {
        delete(Routes.Post.DELETE_POST) {
            val request = call.receiveOrNull<DeletePostRequest>() ?: kotlin.run {
                call.respond(HttpStatusCode.BadRequest)
                return@delete
            }
            try {
                val deleted = postController
                    .deletePost(call.userId, request)
                // TODO: send response
            } catch (e: AppException) {
                // TODO: send response
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}

fun Route.getFriendsPosts(
    followService: FollowService,
    postService: PostService
) {
    authenticate {
        get(Routes.Post.GET_FRIENDS_POSTS) {
            val pageNumber = call.parameters[RequestParam.PAGE_NUMBER]
                ?.toIntOrNull()
                ?: 0
            val pageSize = call.parameters[RequestParam.PAGE_SIZE]
                ?.toIntOrNull()
                ?: Constants.Length.POST_PAGE
            try {
                val friendsIds = followService
                    .findByByWhoId(call.userId)
                    .map { it.otherId }
                val posts = postService.getFriendsPosts(
                    friendsIds = friendsIds,
                    pageNumber = pageNumber,
                    pageSize = pageSize
                )
                // TODO: send the posts
            } catch (e: AppException) {
                // TODO: send response
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
