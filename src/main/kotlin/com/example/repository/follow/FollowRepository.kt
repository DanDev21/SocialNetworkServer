package com.example.repository.follow

import com.example.domain.data.dto.crud.CrudResult.*
import com.example.domain.model.Follow

interface FollowRepository {

    suspend fun add(follow: Follow): InsertResult<Follow>

    suspend fun findById(id: String): FindResult<Follow>

    suspend fun findByIds(userId: String, otherUserId: String): FindResult<Follow>

    suspend fun findByFollowerId(id: String): FindManyResult<Follow>

    suspend fun findByFollowedUserId(id: String): FindManyResult<Follow>

    suspend fun delete(followerId: String, followedUserId: String): DeleteResult<Follow>
}