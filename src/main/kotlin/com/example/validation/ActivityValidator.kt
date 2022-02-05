package com.example.validation

import com.example.domain.data.dto.crud.CrudResult.FindResult
import com.example.domain.model.*
import com.example.domain.util.Action
import com.example.domain.util.AppException.InvalidException
import com.example.domain.util.Validation

class ActivityValidator(
    private val findUser: suspend (String) -> FindResult<User>,
    private val findLike: suspend (String) -> FindResult<Like>,
    private val findFollow: suspend (String) -> FindResult<Follow>,
    private val findComment: suspend (String) -> FindResult<Comment>,
) : Validator<Activity> {

    override suspend fun validate(entity: Activity) {
        if (findUser(entity.targetUserId).failed) {
            throw InvalidException(Validation.USER_ID)
        }

        when (entity.actionInt) {
            Action.LIKED -> {
                if (findLike(entity.targetId).failed) {
                    throw InvalidException(Validation.TARGET_ID)
                }
            }

            Action.STARTED_FOLLOWING -> {
                if (findFollow(entity.targetId).failed) {
                    throw InvalidException(Validation.TARGET_ID)
                }
            }

            Action.COMMENTED -> {
                if (findComment(entity.targetId).failed) {
                    throw InvalidException(Validation.TARGET_ID)
                }
            }

            else -> throw InvalidException(Validation.ACTION_INT)
        }
    }
}