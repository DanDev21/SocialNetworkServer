package com.example.data.repository

import com.example.data.dto.util.CrudResult.*
import com.example.domain.entity.Follow

interface FollowRepository {

    suspend fun add(follow: Follow): InsertResult<Follow>

    suspend fun findById(id: String): FindResult<Follow>

    suspend fun findByIds(userId: String, otherUserId: String): FindResult<Follow>

    suspend fun findByOrderedIds(followerId: String, followedUserId: String): FindResult<Follow>

    suspend fun findByFollowerId(id: String): FindResult<List<Follow>>

    suspend fun findByFollowedUserId(id: String): FindResult<List<Follow>>

    suspend fun delete(followerId: String, followedUserId: String): DeleteResult<Follow>
}