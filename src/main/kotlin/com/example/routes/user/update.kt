package com.example.routes.user

import com.example.core.AppException
import com.example.data.dto.request.user.UpdateProfileRequest
import com.example.core.util.FileManager
import com.example.core.util.Folder
import com.example.core.util.Routes
import com.example.core.util.extensions.getPicture
import com.example.core.util.extensions.receive
import com.example.core.util.extensions.requesterId
import com.example.service.UserService
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.routing.*

fun Route.updateProfile(
    service: UserService
) {
    authenticate {
        put(Routes.User.UPDATE_PROFILE) {
            try {
                val request = call.receive<UpdateProfileRequest>()
                val result = service.update(request, call.requesterId)
                TODO()
            } catch (e: AppException) {
                TODO()
            } catch (e: Exception) {
                TODO()
            }
        }
    }
}

fun Route.updateProfileImage(
    service: UserService
) {
    authenticate {
        put(Routes.User.UPDATE_PROFILE_PICTURE) {
            try {
                val picture = call.getPicture()
                val result = service.update(
                    profileImageUrl = picture.url,
                    userId = call.requesterId
                )

                if (result.succeeded) {
                    FileManager.save(
                        picture = picture,
                        folderPath = Folder.PROFILE_PICTURE
                    )
                }
                TODO()
            } catch (e: AppException) {
                TODO()
            } catch (e: Exception) {
                TODO()
            }
        }
    }
}