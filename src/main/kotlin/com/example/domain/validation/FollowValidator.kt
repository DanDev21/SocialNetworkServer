package com.example.domain.validation

import com.example.domain.model.Follow
import com.example.domain.model.User
import com.example.domain.util.AppException.InvalidException
import com.example.domain.util.Validation

class FollowValidator(
    private val findUserById: suspend (String) -> User?
) : Validator<Follow> {

    override suspend fun validate(entity: Follow) {
        if (entity.byWhoId == entity.otherId ||
            findUserById(entity.byWhoId) == null ||
            findUserById(entity.otherId) == null) {
            throw InvalidException(Validation.USER_ID)
        }
    }
}