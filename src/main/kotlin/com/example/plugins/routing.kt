package com.example.plugins

import com.example.data.dto.util.JwtProperties
import com.example.domain.service.*
import com.example.routes.activity.getUserActivities
import com.example.routes.comment.createComment
import com.example.routes.comment.deleteComment
import com.example.routes.comment.getComments
import com.example.routes.follow.follow
import com.example.routes.follow.unfollow
import com.example.routes.like.like
import com.example.routes.like.unlike
import com.example.routes.post.createPost
import com.example.routes.post.deletePost
import com.example.routes.post.getFollowedPosts
import com.example.routes.post.getUserPosts
import com.example.routes.user.*
import io.ktor.application.*
import io.ktor.http.content.*
import io.ktor.routing.*
import org.koin.ktor.ext.inject

fun Application.configureRouting() {
    registerStaticResources()
    registerUserRoutes()
    registerFollowRoutes()
    registerPostRoutes()
    registerCommentRoutes()
    registerLikeRoutes()
    registerActivityRoutes()
}

private fun Application.registerStaticResources() {
    routing {
        static {
            resources("static")
        }
    }
}

private fun Application.registerUserRoutes() {
    val userService: UserService by inject()
    val jwtProperties = JwtProperties.from(environment.config)

    routing {
        signUp(userService)
        signIn(
            userService,
            jwtProperties
        )
        authenticate()
        updateProfile(userService)
        updateProfileImage(userService)
        getProfile(userService)
    }
}

private fun Application.registerFollowRoutes() {
    val followService: FollowService by inject()
    val activityService: ActivityService by inject()

    routing {
        follow(
            followService,
            activityService
        )
        unfollow(followService)
    }
}

private fun Application.registerPostRoutes() {
    val postService: PostService by inject()

    routing {
        createPost(postService)
        deletePost(postService)
        getUserPosts(postService)
        getFollowedPosts(postService)
    }
}

private fun Application.registerCommentRoutes() {
    val commentService: CommentService by inject()
    val activityService: ActivityService by inject()

    routing {
        createComment(
            commentService,
            activityService
        )
        deleteComment(commentService)
        getComments(commentService)
    }
}

private fun Application.registerLikeRoutes() {
    val likeService: LikeService by inject()
    val activityService: ActivityService by inject()

    routing {
        like(
            likeService,
            activityService
        )
        unlike(likeService)
    }
}

private fun Application.registerActivityRoutes() {
    val activityService: ActivityService by inject()

    routing {
        getUserActivities(activityService)
    }
}
