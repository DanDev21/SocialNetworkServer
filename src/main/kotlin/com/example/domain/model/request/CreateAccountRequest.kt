package com.example.domain.model.request

data class CreateAccountRequest(
    val email: String,
    val username: String,
    val password: String
)
