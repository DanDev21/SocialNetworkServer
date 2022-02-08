package com.example.use_case.user

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.example.domain.data.dto.jwt.JwtProperties
import com.example.domain.data.dto.request.user.SignInRequest
import com.example.domain.model.User
import com.example.domain.util.Authorization
import com.example.domain.util.Time
import com.example.domain.util.Token
import com.example.domain.util.AppException.InvalidException
import com.example.domain.util.AppException.SecurityException
import com.example.repository.user.UserRepository
import java.util.*

class SignIn(
    private val repository: UserRepository
) {

    suspend operator fun invoke(
        request: SignInRequest,
        jwtProperties: JwtProperties
    ): String {
        val user = repository.findByEmailOrUsername(request.emailOrUsername).obj
            ?: throw InvalidException(Authorization.EMAIL_OR_USERNAME_CANNOT_BE_FOUND)

        if (user.password == request.password) {
            return getToken(user, jwtProperties)
        }
        throw SecurityException(Authorization.CREDENTIALS_DO_NOT_MATCH)
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
}