package com.example.routes.post

import com.example.util.Routes
import com.example.data.dto.util.DataWrapper
import com.example.util.FileInterceptor
import com.example.extension.requesterId
import com.example.extension.confirm
import com.example.extension.safe
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
                    .extractDataAndFile<DataWrapper<String>>()
                val result = service.add(tuple, call.requesterId)

                call.confirm(result)
            }
        }
    }
}