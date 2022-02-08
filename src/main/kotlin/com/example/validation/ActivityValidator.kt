package com.example.validation

import com.example.domain.data.dto.crud.CrudResult.FindResult
import com.example.domain.model.*
import com.example.domain.util.Action
import com.example.core.AppException.InvalidException
import com.example.domain.util.Validation

class ActivityValidator(
    private val findUser: suspend (String) -> FindResult<User>,
    private val findLike: suspend (String) -> FindResult<Like>,
    private val findComment: suspend (String) -> FindResult<Comment>,
) : Validator<Activity> {

    override suspend fun validate(entity: Activity) {
        if (findUser(entity.authorId).failed ||
            findUser(entity.targetUserId).failed) {
            throw InvalidException(Validation.USER_ID)
        }

        when (entity.actionInt) {
            Action.LIKED -> {
                if (findLike(entity.targetId).failed) {
                    throw InvalidException(Validation.TARGET_ID)
                }
            }

            Action.COMMENTED -> {
                if (findComment(entity.targetId).failed) {
                    throw InvalidException(Validation.TARGET_ID)
                }
            }

            Action.STARTED_FOLLOWING -> {
                // the user's ids are already validated
            }

            else -> throw InvalidException(Validation.ACTION_INT)
        }
    }
}