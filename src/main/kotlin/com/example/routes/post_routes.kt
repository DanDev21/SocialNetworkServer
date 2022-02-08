package com.example.routes

import com.example.domain.data.dto.request.post.CreatePostRequest
import com.example.domain.data.dto.request.post.DeletePostRequest
import com.example.core.AppException
import com.example.domain.util.Length
import com.example.domain.util.Routes
import com.example.domain.util.extensions.pageNumber
import com.example.domain.util.extensions.pageSize
import com.example.domain.util.extensions.receive
import com.example.domain.util.extensions.requesterId
import com.example.service.PostService
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.routing.*

fun Route.createPost(
    service: PostService
) {
    authenticate {
        post(Routes.Post.CREATE_POST) {
            try {
                val request = call.receive<CreatePostRequest>()
                val result = service.add(request, call.requesterId)
                TODO()
            } catch (e: AppException) {
                TODO()
            } catch (e: Exception) {
                e.printStackTrace()
                TODO()
            }
        }
    }
}

fun Route.deletePost(
    service: PostService
) {
    authenticate {
        delete(Routes.Post.DELETE_POST) {
            try {
                val request = call.receive<DeletePostRequest>()
                val result = service.delete(request, call.requesterId)
                TODO()
            } catch (e: AppException) {
                TODO()
            } catch (e: Exception) {
                e.printStackTrace()
                TODO()
            }
        }
    }
}

fun Route.getUserPosts(
    service: PostService
) {
    authenticate {
        get(Routes.Post.GET_USER_POSTS) {
            try {
                val result = service.getUserPosts(
                    pageNumber = call.pageNumber,
                    pageSize = call.pageSize ?: Length.POST_PAGE,
                    authorId = call.requesterId
                )
                TODO()
            } catch (e: AppException) {
                TODO()
            } catch (e: Exception) {
                e.printStackTrace()
                TODO()
            }
        }
    }
}

fun Route.getFollowedPosts(
    service: PostService
) {
    authenticate {
        get(Routes.Post.GET_FOLLOWED_USERS_POSTS) {
            try {
                val result = service.getFollowedPosts(
                    pageNumber = call.pageNumber,
                    pageSize = call.pageSize ?: Length.POST_PAGE,
                    followerId = call.requesterId
                )
                TODO()
            } catch (e: AppException) {
                TODO()
            } catch (e: Exception) {
                e.printStackTrace()
                TODO()
            }
        }
    }
}