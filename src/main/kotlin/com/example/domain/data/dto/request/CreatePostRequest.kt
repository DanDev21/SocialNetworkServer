package com.example.domain.data.dto.request

data class CreatePostRequest(
    val authorId: String,
    val description: String?,
    val imageUrl: String
)
