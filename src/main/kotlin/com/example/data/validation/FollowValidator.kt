package com.example.data.validation

import com.example.util.AppException.InvalidException
import com.example.util.AppException.InvalidException.Validation
import com.example.data.dto.util.CrudResult.FindResult
import com.example.domain.entity.Follow
import com.example.domain.entity.User

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