package com.example.domain.validation

import com.example.domain.model.*
import com.example.domain.util.AppException.InvalidException
import com.example.domain.util.TargetType
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

        when (TargetType.fromInt(entity.targetType)) {
            TargetType.Post -> {
                if (findPostById(entity.targetId) == null) {
                    message += Validation.TARGET_ID
                }
            }
            TargetType.Comment -> {
                if (findCommentById(entity.targetId) == null) {
                    message += Validation.TARGET_ID
                }
            }
            TargetType.Default -> message += Validation.TARGET_TYPE
        }

        if (message.isNotEmpty()) {
            throw InvalidException(message)
        }
    }
}