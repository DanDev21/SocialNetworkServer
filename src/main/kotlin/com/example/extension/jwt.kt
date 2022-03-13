package com.example.extension

import com.auth0.jwt.JWTCreator
import com.auth0.jwt.algorithms.Algorithm
import com.example.data.dto.util.JwtProperties
import com.example.domain.entity.User
import com.example.util.Time
import com.example.util.Token
import java.util.*


fun JWTCreator.Builder.intendedFor(user: User): JWTCreator.Builder =
    this.withClaim(Token.REQUESTER_ID, user.id)

fun JWTCreator.Builder.signUsing(jwtProperties: JwtProperties): String =
    this.withExpiresAt(Date(System.currentTimeMillis() + Time.ONE_YEAR))
        .withIssuer(jwtProperties.issuer)
        .withAudience(jwtProperties.audience)
        .sign(Algorithm.HMAC256(jwtProperties.secret))