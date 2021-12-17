package com.example.routes

import com.example.domain.model.request.CreatePostRequest
import com.example.domain.model.response.Response
import com.example.domain.util.AppException
import com.example.domain.util.Constants
import com.example.service.post.PostService
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.createPost(postService: PostService) {
    post(Constants.Routes.Post.NEW) {
        val request = call.receiveOrNull<CreatePostRequest>() ?: kotlin.run {
            call.respond(HttpStatusCode.BadRequest)
            return@post
        }

        try {
            postService.add(
                authorId = request.authorId,
                description = request.description,
                imageUrl = request.imageUrl
            )
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
        }
    }
}
