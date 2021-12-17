package com.example.domain.validation

import com.example.domain.model.Follow
import com.example.domain.model.User
import com.example.domain.util.AppException
import com.example.domain.util.Constants

class FollowValidator : Validator<Follow> {

    lateinit var findUserById: suspend (String) -> User?

    override suspend fun validate(entity: Follow) {
        if (entity.byWhoId.isBlank() ||
            entity.otherId.isBlank()) {
            throw AppException.InvalidException(Constants.Error.Invalid.ID)
        }

        if (findUserById(entity.byWhoId) == null ||
            findUserById(entity.otherId) == null) {
            throw AppException.InvalidException(Constants.Error.Invalid.ID)
        }
    }
}