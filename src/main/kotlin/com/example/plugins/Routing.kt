package com.example.plugins

import com.example.domain.data.dto.jwt.JwtProperties
import com.example.domain.util.Property
import com.example.routes.*
import com.example.service.*
import io.ktor.routing.*
import io.ktor.application.*
import org.koin.ktor.ext.inject

fun Application.configureRouting() {
    registerUserRoutes()
    registerFollowRoutes()
    registerPostRoutes()
    registerCommentRoutes()
    registerLikeRoutes()
    registerActivityRoutes()
}

private fun Application.registerUserRoutes() {
    val userService: UserService by inject()
    val jwtProperties = JwtProperties(
        issuer = environment.config.property(Property.DOMAIN).toString(),
        audience = environment.config.property(Property.AUDIENCE).toString(),
        secret = environment.config.property(Property.SECRET).toString()
    )

    routing {
        signUp(userService)
        signIn(
            userService,
            jwtProperties
        )
        findProfile(userService)
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
