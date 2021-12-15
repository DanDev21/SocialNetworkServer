package com.example.repository.user

import com.example.domain.model.User

interface UserRepository {

    suspend fun add(user: User)

    suspend fun getById(id: String): User?

    suspend fun getByEmailOrUsername(email: String, username: String): User?
}