package com.example.validation

interface Validator<T> {

    suspend fun validate(entity: T)
}
