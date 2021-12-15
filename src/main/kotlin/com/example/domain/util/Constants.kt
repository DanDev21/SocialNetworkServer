package com.example.domain.util

object Constants {

    object Length {

        object Min {
            const val USERNAME = 3
            const val PASSWORD = 6
        }
    }

    object Database {
        const val NAME = "social_network"
    }

    object Error {

        object Repository {
            const val USER_ALREADY_EXISTS = "There is already a user with this email or username."
        }

        object Validation {
            const val EMAIL = "The provided email address is invalid."
            const val USERNAME = "The username's length has to be longer than ${Length.Min.USERNAME} characters."
            const val PASSWORD = "The password's length has to be longer than ${Length.Min.PASSWORD} characters."
        }
    }
}