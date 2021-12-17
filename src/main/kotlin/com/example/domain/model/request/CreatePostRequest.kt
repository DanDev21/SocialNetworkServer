package com.example.domain.model.request

data class CreatePostRequest(
    val authorId: String,
    val description: String?,
    val imageUrl: String
)
