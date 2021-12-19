package com.example.service

import com.example.domain.model.Follow
import com.example.domain.validation.FollowValidator
import com.example.repository.follow.FollowRepository
import com.example.repository.user.UserRepository

class FollowService(
    private val userRepository: UserRepository,
    private val followRepository: FollowRepository
) {

    private val followValidator = FollowValidator(userRepository::findById)

    suspend fun add(byWhoId: String, otherId: String) {
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

    suspend fun delete(byWhoId: String, otherId: String) =
        followRepository.delete(byWhoId, otherId)
}