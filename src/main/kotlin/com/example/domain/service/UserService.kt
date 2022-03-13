package com.example.domain.service

import com.example.util.AppException.InvalidException
import com.example.util.AppException.InvalidException.Validation
import com.example.util.Folder
import com.example.util.Url
import com.example.util.FileManager
import com.example.data.dto.request.user.SignInRequest
import com.example.data.dto.request.user.SignupRequest
import com.example.data.dto.request.user.UpdateUserRequest
import com.example.data.dto.util.CrudResult.*
import com.example.data.dto.util.Profile
import com.example.domain.entity.User
import com.example.data.repository.FollowRepository
import com.example.data.repository.UserRepository
import com.example.data.validation.UserValidator

class UserService(
    private val userRepository: UserRepository,
    private val followRepository: FollowRepository
) {

    private val userValidator = UserValidator()
    private val folderManager =
        FileManager.getNewManagerFor(Folder.PROFILE_IMAGES, Url.PROFILE_IMAGES)

    suspend fun signUp(request: SignupRequest): InsertResult<User> {
        val user = User(
            request.email.trim(),
            request.username.trim(),
            request.password.trim()
        )
        userValidator.validate(user)
        return userRepository.add(user)
    }

    suspend fun signIn(
        request: SignInRequest,
    ) = userRepository
        .findByEmailOrUsername(request.emailOrUsername.trim())
        .takeIf { it.data?.password == request.password.trim() }
        ?: throw InvalidException(Validation.CREDENTIALS_DO_NOT_MATCH)

    suspend fun find(
        searchedUserId: String,
        requesterId: String,
    ): Profile {
        val user = userRepository
            .findById(searchedUserId).content
        val isMine = searchedUserId == requesterId
        val following =
            if (isMine) {
                false
            } else {
                followRepository
                    .findByOrderedIds(requesterId, searchedUserId).succeeded
            }

        return Profile.from(
            user = user,
            following = following,
            isMine = isMine
        )
    }

    suspend fun update(
        request: UpdateUserRequest,
        userId: String,
    ): UpdateResult<User> {
        userValidator.validateRequest(request)
        return userRepository.update(userId, request)
    }

    suspend fun update(
        imageData: FileManager.RawFileData,
        userId: String,
    ): UpdateResult<User> {
        folderManager.save(imageData)

        val result = userRepository.update(
            userId = userId,
            profileImageUrl = Url.PROFILE_IMAGES + imageData.fileName,
        )

        if (result.failed) {
            folderManager.delete(imageData.fileName)
        } else {
            folderManager.deleteUsingUrl(result.content.profileImageUrl)
        }

        return result
    }
}