package com.example.data.validation

import com.example.data.dto.util.CrudResult.FindResult
import com.example.data.entity.Follow
import com.example.data.entity.User
import com.example.core.AppException.InvalidException
import com.example.data.util.Validation

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