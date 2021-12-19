package com.example.domain.validation

import com.example.domain.model.Post
import com.example.domain.model.User
import com.example.domain.util.AppException
import com.example.domain.util.AppException.Messages.Validation

class PostValidator(
    private val findUserById: suspend (String) -> User?
) : Validator<Post> {

    override suspend fun validate(entity: Post) {
        if (entity.authorId.isBlank()) {
            throw AppException.InvalidException(Validation.ID)
        }

        if (findUserById(entity.authorId) == null) {
            throw AppException.InvalidException(Validation.ID)
        }

        if (entity.imageUrl.isBlank()) {
            throw AppException.InvalidException(Validation.FIELD)
        }
    }
}