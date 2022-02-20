package com.example.data.validation

import com.example.data.dto.util.CrudResult
import com.example.data.entity.Post
import com.example.data.entity.User
import com.example.core.AppException.InvalidException
import com.example.data.util.Validation

class PostValidator(
    private val findUser: suspend (String) -> CrudResult.FindResult<User>
) : Validator<Post> {

    override suspend fun validate(entity: Post) {
        when {
            findUser(entity.authorId).failed ->     throw InvalidException(Validation.USER_ID)
            entity.imageUrl.isBlank() ->            throw InvalidException(Validation.URL)
        }
    }
}