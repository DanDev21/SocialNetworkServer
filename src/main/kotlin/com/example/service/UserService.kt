package com.example.service

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.example.domain.data.dto.JwtProperties
import com.example.domain.model.User
import com.example.domain.data.dto.request.SignInRequest
import com.example.domain.data.dto.request.SignUpRequest
import com.example.domain.util.AppException.InvalidException
import com.example.domain.util.AppException.Security
import com.example.domain.util.Constants
import com.example.domain.util.Validation
import com.example.domain.validation.UserValidator
import com.example.repository.user.UserRepository
import java.util.*

class UserService(
    private val userRepository: UserRepository
) {

    private val userValidator = UserValidator()

    suspend fun signUp(request: SignUpRequest) {
        val user = User(
            request.email.trim(),
            request.username.trim(),
            request.password.trim()
        )
        userValidator.validate(user)
        userRepository.add(user)
    }

    suspend fun signIn(
        request: SignInRequest,
        jwtProperties: JwtProperties
    ): String {
        val user = userRepository
            .findByCredentials(request.emailOrUsername)
            ?: throw InvalidException(Validation.FIELD)
        if (user.password == request.password) {
            return generateToken(user, jwtProperties)
        } else throw Security.InvalidCredentials
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

    suspend fun findByUsername(username: String) =
        userRepository.findByUsername(username)
}