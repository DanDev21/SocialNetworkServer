package com.example.data.dto.util

import com.example.util.AppException.InvalidException
import com.example.util.AppException.InvalidException.Validation

sealed class CrudResult <T> (open val data: T?) {

    val succeeded: Boolean get() = data != null
    val failed: Boolean get() = data == null

    val content: T get() = data
        ?: throw InvalidException(Validation.TRYING_FORCE_NULL)

    data class InsertResult <T> (override val data: T?)
        : CrudResult <T> (data)

    data class UpdateResult <T> (override val data: T?)
        : CrudResult <T> (data)

    data class FindResult <T> (override val data: T?)
        : CrudResult <T> (data)

    data class DeleteResult <T> (override val data: T?)
        : CrudResult <T> (data)
}
