package com.example.domain.service

import com.example.data.dto.util.CrudResult.InsertResult
import com.example.domain.entity.Follow
import com.example.data.repository.FollowRepository
import com.example.data.repository.UserRepository
import com.example.data.validation.FollowValidator

class FollowService(
    private val followRepository: FollowRepository,
    userRepository: UserRepository,
) {

    private val followValidator =
        FollowValidator(userRepository::findById)

    suspend fun add(
        followedUserId: String,
        followerId: String,
    ): InsertResult<Follow> {
        val follow = Follow(
            followerId = followerId,
            followedUserId = followedUserId,
            timestamp = System.currentTimeMillis()
        )
        followValidator.validate(follow)
        return followRepository.add(follow)
    }

    suspend fun delete(
        followedUserId: String,
        followerId: String,
    ) = followRepository.delete(
        followerId = followerId,
        followedUserId = followedUserId
    )
}