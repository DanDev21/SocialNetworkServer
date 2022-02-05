package com.example.domain.data.dto.jwt

data class JwtProperties(
    val issuer: String,
    val audience: String,
    val secret: String
)
