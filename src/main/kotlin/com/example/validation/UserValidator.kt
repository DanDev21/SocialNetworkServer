package com.example.validation

import com.example.domain.model.User
import com.example.core.AppException.InvalidException
import com.example.domain.util.Length
import com.example.domain.util.Validation

class UserValidator : Validator<User> {

    override suspend fun validate(entity: User) {
        when {
            entity.email.isBlank() ->                       throw InvalidException(Validation.EMAIL)
            Length.Min.USERNAME > entity.username.length -> throw InvalidException(Validation.USERNAME)
            Length.Min.PASSWORD > entity.password.length -> throw InvalidException(Validation.PASSWORD)
        }
    }
}