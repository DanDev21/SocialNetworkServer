package com.example.domain.data.dto.request

data class SignInRequest(
    val emailOrUsername: String,
    val password: String
)
