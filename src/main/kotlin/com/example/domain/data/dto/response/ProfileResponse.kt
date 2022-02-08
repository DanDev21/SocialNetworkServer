package com.example.domain.data.dto.response

data class ProfileResponse(
    val email: String,
    val username: String,

    val bioText: String? = null,
    val profileImageUrl: String? = null,

    val skills: List<String>,

    val github: String? = null,
    val linkedIn: String? = null,
    val instagram: String? = null,

    val followersNo: Int,
    val followedUsersNo: Int,
    val postsNo: Int,

    val isMine: Boolean,
    val following: Boolean,

    val id: String,
)