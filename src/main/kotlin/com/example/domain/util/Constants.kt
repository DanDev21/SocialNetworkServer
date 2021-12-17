package com.example.domain.util

object Constants {

    object Length {

        const val POST_PAGE = 20

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
            const val CREDENTIALS_DO_NOT_MATCH = "The username and the password don't match."

            const val ALREADY_FOLLOW = "You already follow this person."
        }

        object Invalid {
            const val ID = "Invalid id."
            const val FIELD = "Invalid field."
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

        object Follow {
            private const val FOLLOW = "$DEFAULT/follow"

            const val NEW = "$FOLLOW/follow_user"
            const val UNFOLLOW = "$FOLLOW/unfollow_user"
        }

        object Post {
            private const val POST = "$DEFAULT/post"

            const val NEW = "$POST/create_post"
        }
    }
}