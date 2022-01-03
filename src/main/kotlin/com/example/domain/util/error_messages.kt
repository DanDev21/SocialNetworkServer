package com.example.domain.util

object Repo {
    const val NO_RESULTS = "There is no match."

    // user
    const val EMAIL_OR_USERNAME_TAKEN = "The email or the username is already used."

    // follow
    const val ALREADY_FOLLOW = "You already follow this person."
}

object Validation {
    const val ID = "Invalid id."
    const val USER_ID = "Invalid user id."
    const val TARGET_ID = "Invalid target id."
    const val TARGET_TYPE = "Invalid target type."
    const val EMPTY = "Empty field."
    const val FIELD = "Invalid field."
    const val URL = "Invalid url."
    const val LENGTH_EXCEEDED = "Length exceeded."

    // user
    const val EMAIL = "The provided email address is invalid."
    const val USERNAME = "The username's length " +
            "has to be longer than ${Constants.Length.Min.USERNAME} characters."
    const val PASSWORD = "The password's length " +
            "has to be longer than ${Constants.Length.Min.PASSWORD} characters."
}

object Authorization {
    const val CREDENTIALS_DO_NOT_MATCH = "The email/username doesn't match the password"
    const val UNAUTHORIZED_REQUEST = "You're not who you say you are."
    const val TOKEN_ID = "cannot extract the user id from request"
}

