package com.example.routes.post

import com.example.util.Routes
import com.example.data.dto.util.DataWrapper
import com.example.domain.service.PostService
import com.example.extension.*
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.getUserPosts(
    service: PostService
) {
    authenticate {
        get(Routes.Post.GET_USER_POSTS) {
            safe {
                val result = service.getUserPosts(
                    pageNumber = call.pageNumber,
                    pageSize = call.pageSize,
                    authorId = call.requesterId
                )

                call.respond(HttpStatusCode.OK, DataWrapper(result.content))
            }
        }
    }
}

fun Route.getFollowedPosts(
    service: PostService
) {
    authenticate {
        get(Routes.Post.GET_FOLLOWED_USERS_POSTS) {
            safe {
                val result = service.getFollowedPosts(
                    pageNumber = call.pageNumber,
                    pageSize = call.pageSize,
                    followerId = call.requesterId
                )

                call.respond(HttpStatusCode.OK, DataWrapper(result.content))
            }
        }
    }
}