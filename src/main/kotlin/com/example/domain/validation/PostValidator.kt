package com.example.domain.validation

import com.example.domain.model.Post
import com.example.domain.model.User
import com.example.domain.util.AppException
import com.example.domain.util.Constants

class PostValidator : Validator<Post> {

    lateinit var findUserById: suspend (String) -> User?

    override suspend fun validate(entity: Post) {
        if (entity.authorId.isBlank()) {
            throw AppException.InvalidException(Constants.Error.Invalid.ID)
        }

        if (findUserById(entity.authorId) == null) {
            throw AppException.InvalidException(Constants.Error.Invalid.ID)
        }

        if (entity.imageUrl.isBlank()) {
            throw AppException.InvalidException(Constants.Error.Invalid.FIELD)
        }
    }
}