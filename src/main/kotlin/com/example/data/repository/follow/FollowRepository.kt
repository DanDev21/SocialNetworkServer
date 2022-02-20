package com.example.data.repository.follow

import com.example.data.dto.util.CrudResult.*
import com.example.data.entity.Follow

interface FollowRepository {

    suspend fun add(follow: Follow): InsertResult<Follow>

    suspend fun findById(id: String): FindResult<Follow>

    suspend fun findByIds(userId: String, otherUserId: String): FindResult<Follow>

    suspend fun findByOrderedIds(followerId: String, followedUserId: String): FindResult<Follow>

    suspend fun findByFollowerId(id: String): FindManyResult<Follow>

    suspend fun findByFollowedUserId(id: String): FindManyResult<Follow>

    suspend fun delete(followerId: String, followedUserId: String): DeleteResult<Follow>
}