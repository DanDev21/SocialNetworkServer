package com.example.data.dto.request.user

data class SigninRequest(
    val emailOrUsername: String,
    val password: String
)
