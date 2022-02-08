package com.example.repository.user

import com.example.domain.data.dto.crud.CrudResult.*
import com.example.domain.model.User

interface UserRepository {

    suspend fun add(user: User): InsertResult<User>

    suspend fun findById(id: String): FindResult<User>

    suspend fun findByUsername(regex: String, id: String): FindManyResult<User>

    suspend fun findByEmailOrUsername(emailOrUsername: String): FindResult<User>
}