package com.example.routes.post

import com.example.Routes
import com.example.util.FileInterceptor
import com.example.extensions.requesterId
import com.example.extensions.confirm
import com.example.extensions.safe
import com.example.data.dto.request.post.CreatePostRequest
import com.example.domain.service.PostService
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.routing.*

fun Route.createPost(
    service: PostService,
) {
    authenticate {
        post(Routes.Post.CREATE_POST) {
            safe {
                val tuple = FileInterceptor.from(call)
                    .extractRequestAndImageFileData<CreatePostRequest>()
                val result = service.add(tuple, call.requesterId)

                call.confirm(result)
            }
        }
    }
}