package com.example.domain.validation

import com.example.domain.model.Follow
import com.example.domain.model.User
import com.example.domain.util.AppException
import com.example.domain.util.AppException.Messages.Validation

class FollowValidator(
    private val findUserById: suspend (String) -> User?
) : Validator<Follow> {

    override suspend fun validate(entity: Follow) {
        if (entity.byWhoId.isBlank() ||
            entity.otherId.isBlank()) {
            throw AppException.InvalidException(Validation.ID)
        }

        if (findUserById(entity.byWhoId) == null ||
            findUserById(entity.otherId) == null) {
            throw AppException.InvalidException(Validation.ID)
        }
    }
}