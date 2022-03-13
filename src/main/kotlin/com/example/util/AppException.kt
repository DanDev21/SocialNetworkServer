package com.example.util

sealed class AppException(
    override val message: String
) : Exception(message) {

    class RepositoryException(override val message: String) : AppException(message) {

        object Repo {
            const val EMAIL_OR_USERNAME_TAKEN = "The email or the username is already used."
            const val ALREADY_FOLLOW = "You already follow this person."
            const val ALREADY_LIKED = "You already liked this."
        }
    }

    class InvalidException(override val message: String) : AppException(message) {

        object Validation {

            const val TARGET_ID = "Invalid target id."
            const val POST_ID = "Invalid post id."
            const val TARGET_INT = "Invalid target ordinal."

            const val FIELD = "Invalid field."
            const val FIELD_LENGTH_EXCEEDED = "Field's length has been exceeded."

            const val FILE_EXTENSION = "Invalid file extension."

            const val URL = "Invalid url."

            const val USER_ID = "Invalid user id."
            const val AUTHOR_ID = "Invalid author id."
            const val EMAIL = "The provided email address is invalid."
            const val USERNAME = "The username's length has to be longer than ${Length.Min.USERNAME} characters."
            const val PASSWORD = "The password's length has to be longer than ${Length.Min.PASSWORD} characters."

            const val ACTION_INT = "Invalid action ordinal."

            const val NULL_PARAM = "Null parameter."
            const val NULL_ROUTE_PARAM = "Null route parameter."
            const val TRYING_FORCE_NULL = "The parameter is null, it couldn't be found."
            const val NULL_OR_INVALID_REQUEST = "Cannot convert JSON to the specified class."
            const val REQUEST = "Invalid request, content missing."

            const val IMAGE_FILE_SIZE = "Image file is too small."

            const val CREDENTIALS_DO_NOT_MATCH = "The email/username doesn't match the password."
            const val TOKEN_ID = "Cannot extract the user id from request"
        }
    }
}