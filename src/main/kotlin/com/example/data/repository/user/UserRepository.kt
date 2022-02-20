package com.example.data.repository.user

import com.example.data.dto.util.CrudResult.*
import com.example.data.dto.request.user.UpdateProfileRequest
import com.example.data.entity.User

interface UserRepository {

    suspend fun add(user: User): InsertResult<User>

    suspend fun update(userId: String, request: UpdateProfileRequest): UpdateResult<User>

    suspend fun update(userId: String, profileImageUrl: String): UpdateResult<User>

    suspend fun findById(id: String): FindResult<User>

    suspend fun findByUsername(regex: String, requesterId: String): FindManyResult<User>

    suspend fun findByEmailOrUsername(emailOrUsername: String): FindResult<User>
}