package com.example.routes

import com.example.domain.util.Authorization
import com.example.domain.util.Constants
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.auth.jwt.*

val JWTPrincipal.userId: String
    get() = getClaim(Constants.Authentication.ID, String::class)
        ?: throw Exception(Authorization.TOKEN_ID)

val ApplicationCall.userId: String
    get() = principal<JWTPrincipal>()
        ?.userId
        ?: throw Exception(Authorization.TOKEN_ID)
