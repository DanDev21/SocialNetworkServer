package com.example.service

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.example.domain.data.dto.JwtProperties
import com.example.domain.model.User
import com.example.domain.data.dto.request.SignInRequest
import com.example.domain.data.dto.request.SignUpRequest
import com.example.domain.util.Constants
import com.example.domain.validation.UserValidator
import com.example.repository.user.UserRepository
import java.util.*

class UserService(
    private val userRepository: UserRepository
) {

    private val userValidator = UserValidator()

    suspend fun add(request: SignUpRequest) {
        val user = User(
            request.email.trim(),
            request.username.trim(),
            request.password.trim()
        )
        userValidator.validate(user)
        userRepository.add(user)
    }

    suspend fun findById(id: String) =
        userRepository.findById(id)

    suspend fun findByEmail(email: String) =
        userRepository.findByEmail(email)

    suspend fun signIn(
        request: SignInRequest,
        jwtProperties: JwtProperties
    ): String {
        val user = userRepository.findByCredentials(
            emailOrUsername = request.emailOrUsername,
            password = request.password
        )
        return generateToken(user, jwtProperties)
    }

    private fun generateToken(
        user: User,
        jwtProperties: JwtProperties
    ) = JWT.create()
        .withExpiresAt(Date(System.currentTimeMillis() * Constants.Time.ONE_YEAR))
        .withClaim(Constants.Authentication.ID, user.id)
        .withIssuer(jwtProperties.issuer)
        .withAudience(jwtProperties.audience)
        .sign(Algorithm.HMAC256(jwtProperties.secret))
}