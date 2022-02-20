package com.example.service

import com.auth0.jwt.JWT
import com.example.data.dto.util.CrudResult.InsertResult
import com.example.data.dto.util.JwtProperties
import com.example.data.dto.request.user.SignInRequest
import com.example.data.dto.request.user.SignUpRequest
import com.example.data.dto.response.Profile
import com.example.data.entity.User
import com.example.core.AppException
import com.example.core.Authorization
import com.example.data.dto.request.user.UpdateProfileRequest
import com.example.core.util.extensions.using
import com.example.data.repository.follow.FollowRepository
import com.example.data.repository.user.UserRepository
import com.example.data.validation.UserValidator
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class UserService : KoinComponent {

    private val userRepository: UserRepository by inject()
    private val followRepository: FollowRepository by inject()

    private val userValidator = UserValidator()

    suspend fun signIn(
        request: SignInRequest,
        jwtProperties: JwtProperties
    ): String {
        val user = userRepository.findByEmailOrUsername(request.emailOrUsername).obj
            ?. takeIf { it.password == request.password }
            ?: throw AppException.InvalidException(Authorization.CREDENTIALS_DO_NOT_MATCH)
        return JWT.create().using(user, jwtProperties)
    }

    suspend fun signUp(request: SignUpRequest): InsertResult<User> {
        val user = User(
            request.email.trim(),
            request.username.trim(),
            request.password.trim()
        )
        userValidator.validate(user)
        return userRepository.add(user)
    }

    suspend fun find(id: String) =
        userRepository.findById(id)

    suspend fun find(
        regex: String,
        id: String
    ) = userRepository.findByUsername(
        regex = regex,
        requesterId = id
    )

    suspend fun findProfile(
        searchedUserId: String,
        requesterId: String,
    ): Profile {
        val user = userRepository
            .findById(searchedUserId).item

        val following = followRepository
            .findByOrderedIds(requesterId, searchedUserId).founded

        return Profile.from(user, following)
    }

    suspend fun update(
        request: UpdateProfileRequest,
        userId: String,
    ) = userRepository.update(userId, request)

    suspend fun update(
        profileImageUrl: String,
        userId: String,
    ) = userRepository.update(
        userId = userId,
        profileImageUrl = profileImageUrl,
    )
}