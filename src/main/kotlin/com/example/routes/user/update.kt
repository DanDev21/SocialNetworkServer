package com.example.routes.user

import com.example.util.Routes
import com.example.extension.confirm
import com.example.extension.receive
import com.example.extension.requesterId
import com.example.extension.safe
import com.example.util.FileInterceptor
import com.example.data.dto.request.user.UpdateUserRequest
import com.example.domain.service.UserService
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.routing.*

fun Route.updateProfile(
    service: UserService
) {
    authenticate {
        put(Routes.User.UPDATE) {
            safe {
                val request = call.receive<UpdateUserRequest>()
                val result = service
                    .update(request, call.requesterId)

                call.confirm(result)
            }
        }
    }
}

fun Route.updateProfileImage(
    service: UserService
) {
    authenticate {
        put(Routes.User.UPDATE_PROFILE_PICTURE) {
            safe {
                val imageData = FileInterceptor.from(call)
                    .extractImageFileData()

                val result = service.update(
                    imageData = imageData,
                    userId = call.requesterId
                )

                call.confirm(result)
            }
        }
    }
}