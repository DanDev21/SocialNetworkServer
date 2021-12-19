package com.example.domain.util

object Constants {

    object Time {
        const val ONE_YEAR = 1000L * 60L * 60L * 24L * 365L
    }

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

    object Authentication {
        const val ID = "user_id"
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