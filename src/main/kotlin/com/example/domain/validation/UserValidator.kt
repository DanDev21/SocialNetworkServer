package com.example.domain.validation

import com.example.domain.model.User
import com.example.domain.util.Constants

class UserValidator : Validator<User> {

    override fun validate(entity: User) {
        var message = ""

        if (entity.email.isBlank()) {
            message += Constants.Error.Validation.EMAIL
        }

        if (Constants.Length.Min.USERNAME > entity.username.length) {
            message += Constants.Error.Validation.USERNAME
        }

        if (Constants.Length.Min.PASSWORD > entity.password.length) {
            message += Constants.Error.Validation.PASSWORD
        }

        if (message.isNotEmpty()) {
            throw Exception(message)
        }
    }
}