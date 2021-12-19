package com.example.domain.data.dto

data class JwtProperties(
    val issuer: String,
    val audience: String,
    val secret: String
)
