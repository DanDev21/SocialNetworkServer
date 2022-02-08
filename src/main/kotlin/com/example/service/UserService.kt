package com.example.service

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.example.domain.data.dto.crud.CrudResult.InsertResult
import com.example.domain.data.dto.jwt.JwtProperties
import com.example.domain.data.dto.request.user.SignInRequest
import com.example.domain.data.dto.request.user.SignUpRequest
import com.example.domain.data.dto.response.Profile
import com.example.domain.model.User
import com.example.core.AppException
import com.example.domain.util.Authorization
import com.example.domain.util.Time
import com.example.domain.util.Token
import com.example.repository.follow.FollowRepository
import com.example.repository.user.UserRepository
import com.example.validation.UserValidator
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.util.*

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
        return getToken(user, jwtProperties)
    }

    private fun getToken(
        user: User,
        jwtProperties: JwtProperties
    ) = JWT.create()
        .withExpiresAt(Date(System.currentTimeMillis() * Time.ONE_YEAR))
        .withClaim(Token.REQUESTER_ID, user.id)
        .withIssuer(jwtProperties.issuer)
        .withAudience(jwtProperties.audience)
        .sign(Algorithm.HMAC256(jwtProperties.secret))

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
        val isMyProfile = searchedUserId == requesterId
        val user = userRepository.findById(searchedUserId).item
        val following =
            if (isMyProfile) {
                false
            } else {
                followRepository.findByOrderedIds(requesterId, searchedUserId).founded
            }

        return Profile(
            email = user.email,
            username = user.username,
            bio = user.bio,
            profileImageUrl = user.profileImageUrl,
            skills = user.skills,
            github = user.github,
            linkedIn = user.linkedIn,
            instagram = user.instagram,
            followedUsersNo = user.followedUsersNo,
            followersNo = user.followersNo,
            postsNo = user.postsNo,
            isMine = isMyProfile,
            following = following,
            id = user.id
        )
    }
}