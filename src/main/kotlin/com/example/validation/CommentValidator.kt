package com.example.validation

import com.example.domain.data.dto.crud.CrudResult.FindResult
import com.example.domain.model.Comment
import com.example.domain.model.Post
import com.example.domain.model.User
import com.example.core.AppException.InvalidException
import com.example.domain.util.Length
import com.example.domain.util.Validation

class CommentValidator(
    private val findUser: suspend (String) -> FindResult<User>,
    private val findPost: suspend (String) -> FindResult<Post>,
) : Validator<Comment> {

    override suspend fun validate(entity: Comment) {
        findUser(entity.authorId).obj?.also {
            if (it.profileImageUrl != entity.author.userProfileImageUrl ||
                it.username != entity.author.username) {
                throw InvalidException(Validation.FIELD)
            }
        } ?: throw InvalidException(Validation.USER_ID)

        when {
            findPost(entity.postId).failed ->           throw InvalidException(Validation.TARGET_ID)
            Length.Max.COMMENT < entity.body.length ->  throw InvalidException(Validation.FIELD_LENGTH_EXCEEDED)
            entity.body.isBlank() ->                    throw InvalidException(Validation.FIELD)
        }

    }
}