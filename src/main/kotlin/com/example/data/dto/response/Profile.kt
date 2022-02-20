package com.example.data.dto.response

import com.example.data.entity.User

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

    val isMine: Boolean = false,
    val following: Boolean,

    val id: String,
) {

    companion object {

        fun from(
            user: User,
            following: Boolean
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
            id = user.id
        )
    }
}