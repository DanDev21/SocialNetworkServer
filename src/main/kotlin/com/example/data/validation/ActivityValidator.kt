package com.example.data.validation

import com.example.data.dto.util.CrudResult.FindResult
import com.example.Action
import com.example.util.AppException.InvalidException
import com.example.util.AppException.InvalidException.Validation
import com.example.domain.entity.Activity
import com.example.domain.entity.Post
import com.example.domain.entity.User

class ActivityValidator(
    private val findUser: suspend (String) -> FindResult<User>,
    private val findPost: suspend (String) -> FindResult<Post>,
) : Validator<Activity> {

    override suspend fun validate(entity: Activity) {
        if (
            entity.authorId == entity.targetedUserId ||
            findUser(entity.authorId).failed ||
            findUser(entity.targetedUserId).failed
        ) {
            throw InvalidException(Validation.USER_ID)
        }

        when (entity.actionInt) {
            Action.LIKED -> {
                if (entity.targetedPostId == null || findPost(entity.targetedPostId).failed) {
                    throw InvalidException(Validation.TARGET_ID)
                }
            }

            Action.COMMENTED -> {
                if (entity.targetedPostId == null ||
                    findPost(entity.targetedPostId).content.authorId != entity.targetedUserId) {
                    throw InvalidException(Validation.TARGET_ID)
                }
            }

            Action.STARTED_FOLLOWING -> {
                if (entity.targetedPostId != null) {
                    throw InvalidException(Validation.TARGET_ID)
                }
            }

            else -> throw InvalidException(Validation.ACTION_INT)
        }
    }
}