package com.example.plugins

import com.example.routes.*
import com.example.service.user.UserService
import com.example.service.follow.FollowService
import com.example.service.post.PostService
import io.ktor.routing.*
import io.ktor.application.*
import org.koin.ktor.ext.inject

fun Application.configureRouting() {

    val userService: UserService by inject()
    val followService: FollowService by inject()
    val postService: PostService by inject()

    routing {
        // user routes
        signUp(userService)
        signIn(userService)

        // follow routes
        follow(followService)
        unfollow(followService)

        // posts routes
        createPost(postService)
    }
}
