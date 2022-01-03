package com.example.domain.validation

import com.example.domain.model.Comment
import com.example.domain.model.Like
import com.example.domain.model.Post
import com.example.domain.model.User
import com.example.domain.util.AppException.InvalidException
import com.example.domain.util.Constants
import com.example.domain.util.Validation

class LikeValidator(
    private val findUserById: suspend (String) -> User?,
    private val findPostById: suspend (String) -> Post?,
    private val findCommentById: suspend (String) -> Comment?
) : Validator<Like> {

    override suspend fun validate(entity: Like) {
        var message = ""

        if (findUserById(entity.userId) == null) {
            message += Validation.USER_ID
        }

        when (entity.targetType) {
            Constants.Target.POST.ordinal -> {
                if (findPostById(entity.targetId) == null) {
                    message += Validation.TARGET_ID
                }
            }
            Constants.Target.COMMENT.ordinal -> {
                if (findCommentById(entity.targetId) == null) {
                    message += Validation.TARGET_ID
                }
            }
            else -> message += Validation.TARGET_TYPE
        }

        if (message.isNotEmpty()) {
            throw InvalidException(message)
        }
    }
}