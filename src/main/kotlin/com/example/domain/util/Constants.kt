package com.example.domain.util

object Constants {

    enum class Target {
        POST,
        COMMENT,
    }

    object Time {
        const val ONE_YEAR = 1000L * 60L * 60L * 24L * 365L
    }

    object Length {

        const val POST_PAGE = 20

        object Min {
            const val USERNAME = 3
            const val PASSWORD = 6
        }

        object Max {
            const val COMMENT = 200
        }
    }

    object Database {
        const val NAME = "social_network"
    }

    object Authentication {
        const val ID = "user_id"
    }

    object RequestParam {
        const val PAGE_NUMBER = "page_number"
        const val PAGE_SIZE = "page_size"
        const val POST_ID = "post_id"
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

            const val FOLLOW_USER = "$FOLLOW/follow_user"
            const val UNFOLLOW = "$FOLLOW/unfollow_user"
        }

        object Post {

            private const val POST = "$DEFAULT/post"

            const val CREATE_POST = "$POST/create"
            const val DELETE_POST = "$POST/delete"
            const val GET_FRIENDS_POSTS = "$POST/get_friends_posts"
        }

        object Like {

            private const val LIKE = "$DEFAULT/like"

            const val CREATE = "$LIKE/create"
            const val DELETE = "$LIKE/delete"
        }

        object Comment {

            private const val COMMENT = "$DEFAULT/comment"

            const val CREATE = "$COMMENT/create"
            const val DELETE = "$COMMENT/delete"
            const val GET_POST_COMMENTS = "$COMMENT/get_post_comments"
        }
    }
}