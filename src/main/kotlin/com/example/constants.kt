package com.example

object Time {
    const val ONE_YEAR = 1000L * 60L * 60L * 24L * 365L
}

object Length {

    const val PAGINATED_RESOURCE_PAGE = 20

    object Min {
        const val USERNAME = 3
        const val PASSWORD = 6

        const val IMAGE_FILE_SIZE = 125
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

object JwtProperty {
    const val DOMAIN = "jwt.domain"
    const val AUDIENCE = "jwt.audience"
    const val SECRET = "jwt.secret"
    const val REALM = "jwt.realm"
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
    const val PROFILE_IMAGES = "build/resources/main/static/profile_images/"
    const val POST_IMAGES = "build/resources/main/static/post_images/"
}

object Url {

    private const val BASE = "http://192.168.100.3:8080/"

    const val PROFILE_IMAGES = "${BASE}profile_images/"
    const val POST_IMAGES = "${BASE}post_images/"
}

object RouteParams {
    const val ID = "id"
}

object QueryParams {
    const val RAW_DATA = "rawData"
}

object Routes {

    private const val DEFAULT = "/api"

    object User {

        private const val USER = "$DEFAULT/user"

        const val SIGN_UP = "$USER/sign_up"
        const val SIGN_IN = "$USER/sign_in"
        const val AUTHENTICATE = "$USER/authenticate"
        const val GET_PROFILE = "$USER/get_profile/{${RouteParams.ID}}"
        const val UPDATE = "$USER/update"
        const val UPDATE_PROFILE_PICTURE = "$USER/update_profile_image"
    }

    object Follow {

        private const val FOLLOW = "$DEFAULT/follow"

        const val FOLLOW_USER = "$FOLLOW/follow_user/{${RouteParams.ID}}"
        const val UNFOLLOW = "$FOLLOW/unfollow_user/{${RouteParams.ID}}"
    }

    object Post {

        private const val POST = "$DEFAULT/post"

        const val CREATE_POST = "$POST/create"
        const val DELETE_POST = "$POST/delete/{${RouteParams.ID}}"
        const val GET_FOLLOWED_USERS_POSTS = "$POST/get_followed_users_posts"
        const val GET_USER_POSTS = "$POST/get_user_posts"
    }

    object Like {

        private const val LIKE = "$DEFAULT/like"

        const val CREATE = "$LIKE/create"
        const val DELETE = "$LIKE/delete/{${RouteParams.ID}}"
    }

    object Comment {

        private const val COMMENT = "$DEFAULT/comment"

        const val CREATE = "$COMMENT/create"
        const val DELETE = "$COMMENT/delete/{${RouteParams.ID}}"
        const val GET_POST_COMMENTS = "$COMMENT/get_post_comments/{${RouteParams.ID}}"
    }

    object Activity {

        private const val ACTIVITY = "$DEFAULT/activity"

        const val GET_USER_ACTIVITIES = "$ACTIVITY/get_user_activities"
    }
}