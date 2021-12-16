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
            const val EMAIL_TAKEN = "This email is already used."
            const val USERNAME_TAKEN = "This username is already used."
        }

        object Validation {
            const val EMAIL = "The provided email address is invalid."
            const val USERNAME = "The username's length has to be longer than ${Length.Min.USERNAME} characters."
            const val PASSWORD = "The password's length has to be longer than ${Length.Min.PASSWORD} characters."
        }
    }

    object Routes {

        private const val DEFAULT = "/api"

        object User {
            private const val USER = "$DEFAULT/user"

            const val SIGN_UP = "$USER/sign_up"
            const val SIGN_IN = "$USER/sign_in"
        }
    }
}