package com.example.domain.validation

import com.example.domain.model.User
import com.example.domain.util.AppException
import com.example.domain.util.Constants

class UserValidator : Validator<User> {

    override suspend fun validate(entity: User) {
        var message = ""

        if (entity.email.isBlank()) {
            message += Constants.Error.Invalid.EMAIL
        }

        if (Constants.Length.Min.USERNAME > entity.username.length) {
            message += Constants.Error.Invalid.USERNAME
        }

        if (Constants.Length.Min.PASSWORD > entity.password.length) {
            message += Constants.Error.Invalid.PASSWORD
        }

        if (message.isNotEmpty()) {
            throw AppException.InvalidException(message)
        }
    }
}