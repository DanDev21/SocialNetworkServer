package com.example.data.validation

import com.example.data.dto.util.CrudResult.FindResult
import com.example.util.AppException.InvalidException
import com.example.util.AppException.InvalidException.Validation
import com.example.util.Target
import com.example.domain.entity.Comment
import com.example.domain.entity.Like
import com.example.domain.entity.Post
import com.example.domain.entity.User

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