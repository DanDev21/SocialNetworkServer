package com.example.data.validation

import com.example.util.AppException.InvalidException
import com.example.util.AppException.InvalidException.Validation
import com.example.domain.entity.Post

class PostValidator : Validator<Post> {

    override suspend fun validate(entity: Post) {
        when {
            entity.imageUrl.isBlank() -> throw InvalidException(Validation.URL)
        }
    }
}