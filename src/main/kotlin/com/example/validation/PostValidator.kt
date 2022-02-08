package com.example.validation

import com.example.domain.data.dto.crud.CrudResult
import com.example.domain.model.Post
import com.example.domain.model.User
import com.example.core.AppException.InvalidException
import com.example.domain.util.Validation

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