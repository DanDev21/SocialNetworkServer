package com.example.domain.validation

interface Validator<T> {

    suspend fun validate(entity: T)
}
