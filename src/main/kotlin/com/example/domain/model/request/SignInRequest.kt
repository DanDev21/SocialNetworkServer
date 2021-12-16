package com.example.domain.model.request

data class SignInRequest(
    val emailOrUsername: String,
    val password: String
)
