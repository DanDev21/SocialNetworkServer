package com.example.domain.validation

import com.example.domain.util.AppException

interface Validator<T> {

    @Throws(AppException.InvalidException::class)
    fun validate(entity: T)
}