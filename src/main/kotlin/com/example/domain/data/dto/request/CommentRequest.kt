package com.example.domain.data.dto.request

data class CommentRequest(
    val body: String,
    val postId: String,
    val username: String,
    val userProfileImageUrl: String
)
