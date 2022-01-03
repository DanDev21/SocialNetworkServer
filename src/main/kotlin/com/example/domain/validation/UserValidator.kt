package com.example.domain.validation

import com.example.domain.model.User
import com.example.domain.util.AppException.InvalidException
import com.example.domain.util.Constants
import com.example.domain.util.Validation

class UserValidator : Validator<User> {

    override suspend fun validate(entity: User) {
        var message = ""
        when {
            entity.email.isBlank() -> message += Validation.EMAIL
            Constants.Length.Min.USERNAME > entity.username.length -> message += Validation.USERNAME
            Constants.Length.Min.PASSWORD > entity.password.length -> message += Validation.PASSWORD
        }

        if (message.isNotEmpty()) {
            throw InvalidException(message)
        }
    }
}