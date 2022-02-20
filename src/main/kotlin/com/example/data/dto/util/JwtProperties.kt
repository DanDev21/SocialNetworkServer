package com.example.data.dto.util

data class JwtProperties(
    val issuer: String,
    val audience: String,
    val secret: String
)
