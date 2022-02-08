package com.example.controller.user

import com.example.domain.data.dto.response.ProfileResponse
import com.example.use_case.follow.FindFollows
import com.example.use_case.user.FindUsers

class UserController(
    private val uc_findUsers: FindUsers,
    private val uc_findFollows: FindFollows,
) {

    suspend fun findProfile(
        searchableId: String,
        userId: String,
    ): ProfileResponse {
        val isMyProfile = searchableId == userId
        val user = uc_findUsers(searchableId).item
        val following = if (isMyProfile) false
        else {
            uc_findFollows(userId, searchableId).founded
        }

        return ProfileResponse(
            email = user.email,
            username = user.username,
            bioText = user.bio.text,
            profileImageUrl = user.bio.profileImageUrl,
            skills = user.skills,
            github = user.contact.github,
            linkedIn = user.contact.linkedIn,
            instagram = user.contact.instagram,
            followedUsersNo = user.statistics.followedUsersNo,
            followersNo = user.statistics.followersNo,
            postsNo = user.statistics.postsNo,
            isMine = isMyProfile,
            following = following,
            id = user.id
        )
    }
}