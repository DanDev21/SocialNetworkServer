package com.example.domain.util

sealed class AppException(
    override val message: String
) : Exception(message) {

    class RepositoryException(override val message: String) : AppException(message)
    class InvalidException(override val message: String) : AppException(message)
}
