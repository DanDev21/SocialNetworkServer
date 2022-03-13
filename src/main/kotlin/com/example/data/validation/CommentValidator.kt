package com.example.data.validation

import com.example.util.AppException.InvalidException
import com.example.util.AppException.InvalidException.Validation
import com.example.util.Length
import com.example.data.dto.util.CrudResult.FindResult
import com.example.domain.entity.Comment
import com.example.domain.entity.Post
import com.example.domain.entity.User

class CommentValidator(
    private val findUser: suspend (String) -> FindResult<User>,
    private val findPost: suspend (String) -> FindResult<Post>,
) : Validator<Comment> {

    override suspend fun validate(entity: Comment) {
        val author = findUser(entity.authorId).data

        when {
            entity.body.isBlank() ->                                    throw InvalidException(Validation.FIELD)
            Length.Max.COMMENT < entity.body.length ->                  throw InvalidException(Validation.FIELD_LENGTH_EXCEEDED)
            author == null ->                                           throw InvalidException(Validation.AUTHOR_ID)
            author.profileImageUrl != entity.author.profileImageUrl ->  throw InvalidException(Validation.URL)
            author.username != entity.author.username ->                throw InvalidException(Validation.FIELD)
            findPost(entity.postId).failed ->                           throw InvalidException(Validation.POST_ID)
        }

    }
}