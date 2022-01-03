package com.example.repository.user

import com.example.domain.model.User

interface UserRepository {

    suspend fun add(user: User)

    suspend fun findById(id: String): User?

    suspend fun findByCredentials(email: String, username: String): User?

    suspend fun findByCredentials(emailOrUsername: String) =
        findByCredentials(emailOrUsername, emailOrUsername)

    suspend fun findByUsername(username: String): User?
}