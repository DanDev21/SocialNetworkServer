package com.example.service.follow

import com.example.domain.model.Follow
import com.example.domain.validation.FollowValidator
import com.example.repository.follow.FollowRepository
import com.example.repository.user.UserRepository

class FollowServiceImpl(
    override val userRepository: UserRepository,
    override val followRepository: FollowRepository
) : FollowService {

    override val followValidator = FollowValidator().apply {
        findUserById = userRepository::findById
    }

    override suspend fun add(byWhoId: String, otherId: String) {
        val follow = Follow(
            byWhoId = byWhoId,
            otherId = otherId,
            timestamp = System.currentTimeMillis()
        )
        followValidator.validate(follow)
        followRepository.add(follow)
    }

    override suspend fun findByByWhoId(id: String) =
        followRepository.findByByWhoId(id)

    override suspend fun findByOtherId(id: String) =
        followRepository.findByOtherId(id)

    override suspend fun delete(byWhoId: String, otherId: String) =
        followRepository.delete(byWhoId, otherId)
}