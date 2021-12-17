package com.example.service.user

import com.example.domain.model.User
import com.example.domain.validation.CredentialValidator
import com.example.domain.validation.UserValidator
import com.example.repository.user.UserRepository

class UserServiceImpl(
    override val userRepository: UserRepository
) : UserService {

    override val userValidator = UserValidator()
    override val credentialValidator = CredentialValidator()

    override suspend fun add(
        email: String,
        username: String,
        password: String
    ) {
        val user = User(
            email.trim(),
            username.trim(),
            password.trim()
        )
        userValidator.validate(user)
        userRepository.add(user)
    }

    override suspend fun findById(id: String) =
        userRepository.findById(id)

    override suspend fun findByCredentials(
        emailOrUsername: String,
        password: String
    ): User? {
        val trimmedEmailOrUsername = emailOrUsername.trim()
        credentialValidator.validatePassword(password.trim())
        credentialValidator.validateEmailOrUsername(trimmedEmailOrUsername)
        return userRepository.findByCredentials(trimmedEmailOrUsername, password)
    }
}