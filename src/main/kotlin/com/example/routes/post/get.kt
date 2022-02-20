package com.example.routes.post

import com.example.core.AppException
import com.example.core.util.Length
import com.example.core.util.Routes
import com.example.core.util.extensions.pageNumber
import com.example.core.util.extensions.pageSize
import com.example.core.util.extensions.requesterId
import com.example.service.PostService
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.routing.*

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