package com.example.domain.validation

import com.example.domain.model.Comment
import com.example.domain.model.Post
import com.example.domain.model.User
import com.example.domain.util.AppException.InvalidException
import com.example.domain.util.Constants
import com.example.domain.util.Validation

class CommentValidator(
    private val findUserById: suspend (String) -> User?,
    private val findPostById: suspend (String) -> Post?
) : Validator<Comment> {

    override suspend fun validate(entity: Comment) {
        var message = ""
        when {
            findUserById(entity.authorId) == null -> message += Validation.USER_ID
            findPostById(entity.postId) == null -> message += Validation.TARGET_ID
            Constants.Length.Max.COMMENT < entity.body.length ->
                message += Validation.LENGTH_EXCEEDED
            entity.body.isBlank() -> message += Validation.FIELD
        }

        if (message.isNotEmpty()) {
            throw InvalidException(message)
        }
    }
}