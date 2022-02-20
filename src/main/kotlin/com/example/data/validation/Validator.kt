package com.example.data.validation

interface Validator<T> {

    suspend fun validate(entity: T)
}
