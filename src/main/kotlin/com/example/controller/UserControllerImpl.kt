package com.example.controller

import com.example.domain.model.User
import com.example.domain.validation.UserValidator
import com.example.repository.user.UserRepository

class UserControllerImpl(
    private val userRepository: UserRepository
) : UserController {

    private val userValidator = UserValidator()

    override suspend fun add(
        email: String,
        username: String,
        password: String
    ) {
        val user = User(
            email,
            username,
            password
        )
        userValidator.validate(user)
        userRepository.add(user)
    }

    override suspend fun getById(id: String) =
        this.userRepository.getById(id)
}