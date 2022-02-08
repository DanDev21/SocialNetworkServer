package com.example.validation

import com.example.domain.data.dto.crud.CrudResult.FindResult
import com.example.domain.model.Follow
import com.example.domain.model.User
import com.example.core.AppException.InvalidException
import com.example.domain.util.Validation

class FollowValidator(
    private val findUser: suspend (String) -> FindResult<User>
) : Validator<Follow> {

    override suspend fun validate(entity: Follow) {
        if (entity.followerId == entity.followedUserId ||
            findUser(entity.followerId).failed ||
            findUser(entity.followedUserId).failed
        ) {
            throw InvalidException(Validation.USER_ID)
        }
    }
}