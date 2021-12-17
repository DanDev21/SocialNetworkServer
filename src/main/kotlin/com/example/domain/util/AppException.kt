package com.example.domain.util

sealed class AppException(
    override val message: String
) : Exception(message) {

    sealed class Repo(
        override val message: String
    ) : AppException(message) {

        object EmailTaken : Repo(Constants.Error.Repository.EMAIL_TAKEN)
        object UsernameTaken : Repo(Constants.Error.Repository.USERNAME_TAKEN)
        object CredentialsDoNotMatch : Repo(Constants.Error.Repository.CREDENTIALS_DO_NOT_MATCH)

        object AlreadyFollow: Repo(Constants.Error.Repository.ALREADY_FOLLOW)
    }

    class InvalidException(override val message: String) : AppException(message)
}
