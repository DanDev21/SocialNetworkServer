package com.example.routes

import com.example.domain.data.dto.request.CreatePostRequest
import com.example.domain.data.dto.response.Response
import com.example.domain.util.AppException
import com.example.domain.util.AppException.Messages
import com.example.domain.util.Constants
import com.example.service.PostService
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.auth.jwt.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.createPost(
    postService: PostService,
) {
    authenticate {
        post(Constants.Routes.Post.NEW) {
            val request = call.receiveOrNull<CreatePostRequest>() ?: kotlin.run {
                call.respond(HttpStatusCode.BadRequest)
                return@post
            }
            try {
                val id = call.principal<JWTPrincipal>()
                    ?.getClaim(Constants.Authentication.ID, String::class)
                    ?: throw Exception()

                if (id == request.authorId) {
                    postService.add(request)
                } else {
                    call.respond(
                        status = HttpStatusCode.Unauthorized,
                        message = Messages.Security.UNAUTHORIZED
                    )
                }
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
