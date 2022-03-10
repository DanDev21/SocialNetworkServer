package com.example.data.repository

import com.example.data.dto.util.CrudResult.*
import com.example.data.dto.request.user.UpdateUserRequest
import com.example.domain.entity.User

interface UserRepository {

    suspend fun add(user: User): InsertResult<User>

    suspend fun update(userId: String, request: UpdateUserRequest): UpdateResult<User>

    suspend fun update(userId: String, profileImageUrl: String): UpdateResult<User>

    suspend fun findById(id: String): FindResult<User>

    suspend fun findByUsername(regex: String, requesterId: String): FindResult<List<User>>

    suspend fun findByEmailOrUsername(emailOrUsername: String): FindResult<User>
}