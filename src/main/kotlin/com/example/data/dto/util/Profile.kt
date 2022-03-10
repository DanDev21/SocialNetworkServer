package com.example.data.dto.util

import com.example.domain.entity.User

data class Profile(
    val email: String,
    val username: String,

    val bio: String? = null,
    val profileImageUrl: String? = null,

    val skills: List<String>,

    val github: String? = null,
    val linkedIn: String? = null,
    val instagram: String? = null,

    val followersNo: Int,
    val followedUsersNo: Int,
    val postsNo: Int,

    val following: Boolean,
    val isMine: Boolean,

    val id: String,
) {

    companion object {

        fun from(
            user: User,
            following: Boolean,
            isMine: Boolean = false,
        ) = Profile(
            email = user.email,
            username = user.username,
            bio = user.bio,
            profileImageUrl = user.profileImageUrl,
            skills = user.skills,
            github = user.github,
            linkedIn = user.linkedIn,
            instagram = user.instagram,
            followedUsersNo = user.followedUsersNo,
            followersNo = user.followersNo,
            postsNo = user.postsNo,
            following = following,
            isMine = isMine,
            id = user.id
        )
    }
}