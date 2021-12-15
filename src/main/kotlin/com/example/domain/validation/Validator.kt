package com.example.domain.validation

interface Validator<T> {

    @Throws(Exception::class)
    fun validate(entity: T)
}