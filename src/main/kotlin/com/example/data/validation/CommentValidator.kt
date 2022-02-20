package com.example.data.validation

import com.example.data.dto.util.CrudResult.FindResult
import com.example.data.entity.Comment
import com.example.data.entity.Post
import com.example.data.entity.User
import com.example.core.AppException.InvalidException
import com.example.core.Validation
import com.example.core.util.Length

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