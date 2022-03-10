package com.example.data.validation

import com.example.util.AppException.InvalidException
import com.example.util.AppException.InvalidException.Validation
import com.example.Length
import com.example.data.dto.request.user.UpdateUserRequest
import com.example.domain.entity.User

class UserValidator : Validator<User> {

    override suspend fun validate(entity: User) {
        when {
            entity.email.isBlank() ->                 throw InvalidException(Validation.EMAIL)
            Length.Min.USERNAME > entity.username.length -> throw InvalidException(Validation.USERNAME)
            Length.Min.PASSWORD > entity.password.length -> throw InvalidException(Validation.PASSWORD)
        }
    }

    fun validateRequest(request: UpdateUserRequest) {
        when {
            Length.Min.USERNAME > request.username.length -> {
                throw InvalidException(Validation.FIELD)
            }

            else -> validateSkills(request.skills)
        }
    }

    private fun validateSkills(skills: List<String>) {
        for (skill in skills) {
            // TODO: check each received skill for it's correction
        }
    }
}