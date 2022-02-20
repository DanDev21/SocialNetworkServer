package com.example.data.validation

import com.example.data.dto.util.CrudResult.FindResult
import com.example.data.entity.*
import com.example.core.AppException.InvalidException
import com.example.core.util.Target
import com.example.data.util.Validation

class LikeValidator(
    private val findUser: suspend (String) -> FindResult<User>,
    private val findPost: suspend (String) -> FindResult<Post>,
    private val findComment: suspend (String) -> FindResult<Comment>,
) : Validator<Like> {

    override suspend fun validate(entity: Like) {
        if (findUser(entity.authorId).failed) {
            throw InvalidException(Validation.USER_ID)
        }

        when (entity.targetInt) {
            Target.POST -> {
                if (findPost(entity.targetId).failed) {
                    throw InvalidException(Validation.TARGET_ID)
                }
            }

            Target.COMMENT -> {
                if (findComment(entity.targetId).failed) {
                    throw InvalidException(Validation.TARGET_ID)
                }
            }

            else -> throw InvalidException(Validation.TARGET_INT)
        }
    }
}