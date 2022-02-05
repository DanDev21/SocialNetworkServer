package com.example.use_case.user

import com.example.domain.data.dto.request.user.SignUpRequest
import com.example.domain.model.User
import com.example.domain.util.AppException
import com.example.validation.UserValidator
import com.example.repository.user.UserRepository

class SignUp(
    private val repository: UserRepository,
) {

    private val validator = UserValidator()

    suspend operator fun invoke(request: SignUpRequest) {
        val user = User(
            request.email.trim(),
            request.username.trim(),
            request.password.trim()
        )
        validator.validate(user)
        repository.add(user)
    }
}