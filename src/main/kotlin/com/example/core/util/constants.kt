package com.example.core.util

object Time {
    const val ONE_YEAR = 1000L * 60L * 60L * 24L * 365L
}

object Length {

    const val POST_PAGE = 20
    const val ACTIVITY_PAGE = 15

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

object Token {
    const val REQUESTER_ID = "requester_id"
}

object Property {
    const val DOMAIN = "jwt.domain"
    const val AUDIENCE = "jwt.audience"
    const val SECRET = "jwt.secret"
}

object RequestParams {
    const val PAGE_NUMBER = "page_number"
    const val PAGE_SIZE = "page_size"
    const val POST_ID = "post_id"
    const val USERNAME_RGX = "username_regex"
    const val OTHER_USER_ID = "other_user_id"
}

object Target {
    const val POST = 0
    const val COMMENT = 1
}

object Action {
    const val LIKED = 0
    const val COMMENTED = 1
    const val STARTED_FOLLOWING = 2
}

object Folder {
    const val PROFILE_PICTURE = "build/resources/main/static/profile_picture/"
    const val POST = "build/resources/main/static/post/"
}

object Url {

    private const val BASE = "http://192.168.0.156:8080/"

    const val PROFILE_PICTURE = "${BASE}profile_pictures/"
    const val POST = "${BASE}post/"
}

object Routes {

    private const val DEFAULT = "/api"

    object User {

        private const val USER = "$DEFAULT/user"

        const val SIGN_UP = "$USER/sign_up"
        const val SIGN_IN = "$USER/sign_in"
        const val FIND_PROFILE = "$USER/find_profile"
        const val UPDATE_PROFILE = "$USER/update_profile"
        const val UPDATE_PROFILE_PICTURE = "$USER/update_profile_picture"
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
        const val GET_FOLLOWED_USERS_POSTS = "$POST/get_friends_posts"
        const val GET_USER_POSTS = "$POST/get_user_posts"
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

    object Activity {

        private const val ACTIVITY = "$DEFAULT/activity"

        const val GET_USER_ACTIVITIES = "$ACTIVITY/get_user_activities"
    }
}
