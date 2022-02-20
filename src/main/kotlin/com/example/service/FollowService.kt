package com.example.service

import com.example.data.dto.util.CrudResult.*
import com.example.data.dto.request.follow.FollowRequest
import com.example.data.dto.request.follow.UnfollowRequest
import com.example.data.entity.Follow
import com.example.data.repository.follow.FollowRepository
import com.example.data.repository.user.UserRepository
import com.example.data.validation.FollowValidator
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class FollowService(
    userRepository: UserRepository,
) : KoinComponent {

    private val followRepository: FollowRepository by inject()

    private val followValidator =
        FollowValidator(userRepository::findById)

    suspend fun add(
        request: FollowRequest,
        followerId: String,
    ): InsertResult<Follow> {
        val follow = Follow(
            followerId = followerId,
            followedUserId = request.followedUserId,
            timestamp = System.currentTimeMillis()
        )
        followValidator.validate(follow)
        return followRepository.add(follow)
    }

    suspend fun delete(
        request: UnfollowRequest,
        followerId: String,
    ) = followRepository.delete(
        followerId = followerId,
        followedUserId = request.followedUserId
    )
}