package com.example.data.dto.request.comment

data class CommentRequest(
    val body: String,
    val postId: String,
    val username: String,
    val userProfileImageUrl: String
)
