package com.example.service.follow

import com.example.domain.model.Follow
import com.example.domain.util.AppException
import com.example.repository.follow.FollowRepository
import com.example.repository.user.UserRepository

interface FollowService {

    val userRepository: UserRepository
    val followRepository: FollowRepository

    @Throws(AppException.InvalidException::class)
    suspend fun add(byWhoId: String, otherId: String)

    suspend fun findByByWhoId(id: String): List<Follow>

    suspend fun findByOtherId(id: String): List<Follow>

    suspend fun delete(byWhoId: String, otherId: String): Boolean
}