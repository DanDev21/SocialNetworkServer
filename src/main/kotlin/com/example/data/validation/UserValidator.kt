package com.example.data.validation

import com.example.data.entity.User
import com.example.core.AppException.InvalidException
import com.example.core.util.Length
import com.example.data.util.Validation

class UserValidator : Validator<User> {

    override suspend fun validate(entity: User) {
        when {
            entity.email.isBlank() ->                       throw InvalidException(Validation.EMAIL)
            Length.Min.USERNAME > entity.username.length -> throw InvalidException(Validation.USERNAME)
            Length.Min.PASSWORD > entity.password.length -> throw InvalidException(Validation.PASSWORD)
        }
    }
}