package com.example.routes.post

import com.example.core.AppException
import com.example.core.util.Routes
import com.example.core.util.extensions.receive
import com.example.core.util.extensions.requesterId
import com.example.data.dto.request.post.DeletePostRequest
import com.example.service.PostService
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.routing.*

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