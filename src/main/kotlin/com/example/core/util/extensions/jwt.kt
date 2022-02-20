package com.example.core.util.extensions

import com.auth0.jwt.JWTCreator
import com.auth0.jwt.algorithms.Algorithm
import com.example.data.dto.util.JwtProperties
import com.example.data.entity.User
import com.example.core.util.Time
import com.example.core.util.Token
import java.util.*

fun JWTCreator.Builder.using(
    user: User,
    jwtProperties: JwtProperties,
): String =
    this.withExpiresAt(Date(System.currentTimeMillis() * Time.ONE_YEAR))
        .withClaim(Token.REQUESTER_ID, user.id)
        .withIssuer(jwtProperties.issuer)
        .withAudience(jwtProperties.audience)
        .sign(Algorithm.HMAC256(jwtProperties.secret))