package com.example.domain.data.dto.request.user

data class SignUpRequest(
    val email: String,
    val username: String,
    val password: String
)