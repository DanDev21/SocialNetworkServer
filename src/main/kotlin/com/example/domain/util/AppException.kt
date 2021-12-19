package com.example.domain.util

sealed class AppException(
    override val message: String
) : Exception(message) {

    sealed class Repo(
        override val message: String
    ) : AppException(message) {

        object EmailTaken : Repo(Messages.Repo.EMAIL_TAKEN)
        object UsernameTaken : Repo(Messages.Repo.USERNAME_TAKEN)
        object CredentialsDoNotMatch : Repo(Messages.Repo.INVALID_CREDENTIALS)

        object AlreadyFollow: Repo(Messages.Repo.ALREADY_FOLLOW)
    }

    class InvalidException(override val message: String) : AppException(message)

    object SecurityException : AppException(Messages.Security.UNAUTHORIZED)

    object Messages {

        object Repo {
            const val EMAIL_TAKEN = "This email is already used."
            const val USERNAME_TAKEN = "This username is already used."
            const val INVALID_CREDENTIALS = "The username and the password don't match."
            const val ALREADY_FOLLOW = "You already follow this person."
        }

        object Validation {
            const val ID = "Invalid id."
            const val EMPTY = "Empty field."
            const val FIELD = "Invalid field."
            const val EMAIL = "The provided email address is invalid."
            const val USERNAME = "The username's length " +
                    "has to be longer than ${Constants.Length.Min.USERNAME} characters."
            const val PASSWORD = "The password's length " +
                    "has to be longer than ${Constants.Length.Min.PASSWORD} characters."
        }

        object Security {
            const val UNAUTHORIZED = "You're not who you say you are."
        }
    }
}
