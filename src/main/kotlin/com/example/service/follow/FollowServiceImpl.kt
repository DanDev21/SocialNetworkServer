package com.example.service.follow

import com.example.domain.model.Follow
import com.example.domain.util.AppException
import com.example.domain.util.Constants
import com.example.repository.follow.FollowRepository
import com.example.repository.user.UserRepository

class FollowServiceImpl(
    override val userRepository: UserRepository,
    override val followRepository: FollowRepository
) : FollowService {

    override suspend fun add(byWhoId: String, otherId: String) {
        if (userRepository.findById(byWhoId) == null) {
            throw AppException.InvalidException(Constants.Error.Validation.INVALID_ID)
        }
        if (userRepository.findById(otherId) == null) {
            throw AppException.InvalidException(Constants.Error.Validation.INVALID_ID)
        }
        val follow = Follow(
            byWhoId = byWhoId,
            otherId = otherId,
            timestamp = System.currentTimeMillis()
        )
        followRepository.add(follow)
    }

    override suspend fun findByByWhoId(id: String) =
        followRepository.findByByWhoId(id)

    override suspend fun findByOtherId(id: String) =
        followRepository.findByOtherId(id)

    override suspend fun delete(byWhoId: String, otherId: String) =
        followRepository.delete(byWhoId, otherId)
}