package com.example.plugins

import com.example.controller.PostController
import com.example.domain.data.dto.JwtProperties
import com.example.routes.*
import com.example.service.*
import io.ktor.routing.*
import io.ktor.application.*
import org.koin.ktor.ext.inject

fun Application.configureRouting() {

    val userService: UserService by inject()
    val followService: FollowService by inject()
    val postService: PostService by inject()
    val likeService: LikeService by inject()
    val commentService: CommentService by inject()

    val postController: PostController by inject()

    val jwtProperties = JwtProperties(
        issuer = environment.config.property("jwt.domain").toString(),
        audience = environment.config.property("jwt.audience").toString(),
        secret = environment.config.property("jwt.secret").toString()
    )

    routing {
        // user routes
        signUp(userService)
        signIn(
            userService = userService,
            jwtProperties = jwtProperties
        )

        // follow routes
        follow(followService)
        unfollow(followService)

        // posts routes
        createPost(postService)
        deletePost(postController)
        getFriendsPosts(
            followService = followService,
            postService = postService
        )

        // like routes
        like(likeService)
        unlike(likeService)

        // comment routes
        createComment(commentService)
        deleteComment(postController)
        getPostsComments(commentService)
    }
}
