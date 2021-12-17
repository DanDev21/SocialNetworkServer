package com.example.service.user

import com.example.domain.model.Credential
import com.example.domain.model.User
import com.example.domain.util.AppException
import com.example.domain.validation.UserValidator
import com.example.repository.user.UserRepository

interface UserService {

    val userRepository: UserRepository
    val userValidator: UserValidator

    @Throws(AppException::class)
    suspend fun add(
        email: String,
        username: String,
        password: String
    )

    suspend fun findById(id: String): User?

    suspend fun findByEmail(email: String): User?

    @Throws(AppException.Repo.CredentialsDoNotMatch::class)
    suspend fun findByCredentials(credential: Credential): User
}