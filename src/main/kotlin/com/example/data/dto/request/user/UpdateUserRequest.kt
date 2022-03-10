package com.example.data.dto.request.user

data class UpdateUserRequest(
    val username: String,
    val bio: String,
    val github: String,
    val linkedIn: String,
    val instagram: String,
    val skills: List<String>,
)