package com.example.routes

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