package com.example.domain.data.dto.request.user

data class SignInRequest(
    val emailOrUsername: String,
    val password: String
)
