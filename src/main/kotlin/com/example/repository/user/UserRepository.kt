package com.example.repository.user

import com.example.domain.model.User
import com.example.domain.util.AppException

interface UserRepository {

    @Throws(AppException.Repo::class)
    suspend fun add(user: User)

    suspend fun findById(id: String): User?

    suspend fun findByEmail(email: String): User?

    suspend fun findByUsername(username: String): User?

    @Throws(AppException.Repo::class)
    suspend fun findByCredentials(emailOrUsername: String, password: String): User
}