package com.example.routes.post

import com.example.Routes
import com.example.extensions.receive
import com.example.extensions.requesterId
import com.example.extensions.safe
import com.example.data.dto.request.PaginatedResourceRequest
import com.example.data.dto.response.ListResponse
import com.example.domain.service.PostService
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
                val request = call.receive<PaginatedResourceRequest>()
                val result = service.getUserPosts(request, call.requesterId)

                call.respond(HttpStatusCode.OK, ListResponse(result))
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
                val request = call.receive<PaginatedResourceRequest>()
                val result = service.getFollowedPosts(request, call.requesterId)

                call.respond(HttpStatusCode.OK, ListResponse(result))
            }
        }
    }
}