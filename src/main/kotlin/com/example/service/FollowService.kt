package com.example.service

import com.example.domain.data.dto.request.UnfollowRequest
import com.example.domain.model.Follow
import com.example.domain.validation.FollowValidator
import com.example.repository.follow.FollowRepository
import com.example.repository.user.UserRepository

class FollowService(
    private val followRepository: FollowRepository,
    userRepository: UserRepository
) {

    private val followValidator =
        FollowValidator(userRepository::findById)

    suspend fun add(
        byWhoId: String,
        otherId: String
    ) {
        val follow = Follow(
            byWhoId = byWhoId,
            otherId = otherId,
            timestamp = System.currentTimeMillis()
        )
        followValidator.validate(follow)
        followRepository.add(follow)
    }

    suspend fun findByByWhoId(id: String) =
        followRepository.findByByWhoId(id)

    suspend fun findByOtherId(id: String) =
        followRepository.findByOtherId(id)

    suspend fun delete(userId: String, request: UnfollowRequest) =
        followRepository.delete(userId, request.followId)
}