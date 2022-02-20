package com.example.core

import com.example.core.util.Length

sealed class AppException(
    override val message: String
) : Exception(message) {

    class RepositoryException(
        override val message: String
    ) : AppException(message)

    class SecurityException(
        override val message: String
    ) : AppException(message)

    class InvalidException(
        override val message: String
    ) : AppException(message)
}

object Repo {
    const val EMAIL_OR_USERNAME_TAKEN = "The email or the username is already used."
    const val ALREADY_FOLLOW = "You already follow this person."
    const val ALREADY_LIKED = "You already liked this."
}

object Validation {
    const val USER_ID = "Invalid user id."
    const val TARGET_ID = "Invalid target id."
    const val TARGET_INT = "Invalid target ordinal."
    const val FIELD_EMPTY = "Empty field."
    const val FIELD = "Invalid field."
    const val URL = "Invalid url."
    const val FIELD_LENGTH_EXCEEDED = "Field's length has been exceeded."
    const val EMAIL = "The provided email address is invalid."
    const val USERNAME = "The username's length has to be longer than ${Length.Min.USERNAME} characters."
    const val PASSWORD = "The password's length has to be longer than ${Length.Min.PASSWORD} characters."
    const val ACTION_INT = "Invalid action ordinal."
    const val NULL_PARAM = "null parameter."
    const val NULL_REQUEST = "null request."
}

object Authorization {
    const val CREDENTIALS_DO_NOT_MATCH = "The email/username doesn't match the password."
    const val UNAUTHORIZED_REQUEST = "You're not who you say you are."
    const val TOKEN_ID = "Cannot extract the user id from request"
}