package com.example.data.dto.request.user

data class SignInRequest(
    val emailOrUsername: String,
    val password: String
)
