package com.example.service.user

import com.example.domain.model.User
import com.example.domain.validation.CredentialValidator
import com.example.domain.validation.UserValidator
import com.example.repository.user.UserRepository

interface UserService {

    val userRepository: UserRepository
    val userValidator: UserValidator
    val credentialValidator: CredentialValidator

    suspend fun add(
        email: String,
        username: String,
        password: String
    )

    suspend fun findById(id: String): User?

    suspend fun findByCredentials(emailOrUsername: String, password: String): User?
}