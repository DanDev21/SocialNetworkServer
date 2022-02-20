package com.example.routes.post

import com.example.core.AppException
import com.example.core.util.Routes
import com.example.core.util.extensions.receive
import com.example.core.util.extensions.requesterId
import com.example.data.dto.request.post.CreatePostRequest
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