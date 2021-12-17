package com.example.repository.follow

import com.example.domain.model.Follow
import com.example.domain.util.AppException

interface FollowRepository {

    @Throws(AppException.RepositoryException::class)
    suspend fun add(follow: Follow)

    suspend fun findByIds(byWhoId: String, otherId: String): Follow?

    suspend fun findByByWhoId(id: String): List<Follow>

    suspend fun findByOtherId(id: String): List<Follow>

    suspend fun delete(byWhoId: String, otherId: String): Boolean
}