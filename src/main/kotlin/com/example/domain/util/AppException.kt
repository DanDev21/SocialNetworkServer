package com.example.domain.util

sealed class AppException(
    override val message: String
) : Exception(message) {

    sealed class Repository(
        override val message: String
    ) : AppException(message) {

        object NoResults : Repository(Repo.NO_RESULTS)

        // user
        object EmailOrUsernameTaken: Repository(Repo.EMAIL_OR_USERNAME_TAKEN)

        // follow
        object AlreadyFollow: Repository(Repo.ALREADY_FOLLOW)
    }

    sealed class Security(
        override val message: String
    ) : AppException(message) {
        object InvalidCredentials : AppException(Authorization.CREDENTIALS_DO_NOT_MATCH)
        object UnauthorizedRequest : AppException(Authorization.UNAUTHORIZED_REQUEST)
    }

    class InvalidException(
        override val message: String
    ) : AppException(message)
}
