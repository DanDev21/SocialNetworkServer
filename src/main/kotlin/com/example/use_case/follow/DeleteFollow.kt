package com.example.use_case.follow

import com.example.domain.data.dto.request.follow.UnfollowRequest
import com.example.repository.follow.FollowRepository

class DeleteFollow(
    private val repository: FollowRepository
) {

    suspend operator fun invoke(
        request: UnfollowRequest,
        followerId: String
    ) = repository.delete(followerId, request.followedUserId)
}