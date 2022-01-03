package com.example.domain.validation

import com.example.domain.model.Post
import com.example.domain.model.User
import com.example.domain.util.AppException.InvalidException
import com.example.domain.util.Validation

class PostValidator(
    private val findUserById: suspend (String) -> User?
) : Validator<Post> {

    override suspend fun validate(entity: Post) {
        var message = ""
        when {
            findUserById(entity.authorId) == null -> message += Validation.USER_ID
            entity.imageUrl.isBlank() -> message += Validation.URL
        }

        if (message.isNotEmpty()) {
            throw InvalidException(message)
        }
    }
}