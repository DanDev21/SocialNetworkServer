package com.example.plugins

import com.example.domain.data.dto.JwtProperties
import com.example.routes.*
import com.example.service.FollowService
import com.example.service.PostService
import com.example.service.UserService
import io.ktor.routing.*
import io.ktor.application.*
import org.koin.ktor.ext.inject

fun Application.configureRouting() {

    val userService: UserService by inject()
    val followService: FollowService by inject()
    val postService: PostService by inject()

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
    }
}
