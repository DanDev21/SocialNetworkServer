package com.example.plugins

import com.example.controller.post.ActivityController
import com.example.controller.post.PostController
import com.example.domain.data.dto.jwt.JwtProperties
import com.example.domain.util.Property
import com.example.routes.*
import com.example.use_case.activity.GetActivities
import com.example.use_case.comment.GetComments
import com.example.use_case.follow.CreateFollow
import com.example.use_case.follow.DeleteFollow
import com.example.use_case.like.DeleteLikes
import com.example.use_case.post.CreatePost
import com.example.use_case.user.FindUsers
import com.example.use_case.user.SignIn
import com.example.use_case.user.SignUp
import io.ktor.routing.*
import io.ktor.application.*
import org.koin.ktor.ext.inject

fun Application.configureRouting() {
    val postController: PostController by inject()
    val activityController: ActivityController by inject()

    configureUserRoutes()
    configureFollowRoutes()
    configurePostRoutes(postController)
    configureCommentRoutes(postController, activityController)
    configureLikeRoutes(activityController)
    configureActivityRoutes()
}

private fun Application.configureUserRoutes() {
    val signUpUseCase: SignUp by inject()
    val signInUseCase: SignIn by inject()
    val findUsersUseCase: FindUsers by inject()
    val jwtProperties = JwtProperties(
        issuer = environment.config.property(Property.DOMAIN).toString(),
        audience = environment.config.property(Property.AUDIENCE).toString(),
        secret = environment.config.property(Property.SECRET).toString()
    )

    routing {
        signUp(signUpUseCase)
        signIn(
            signIn = signInUseCase,
            jwtProperties = jwtProperties
        )
        findUsers(findUsersUseCase)
    }
}

private fun Application.configureFollowRoutes() {
    val createFollow: CreateFollow by inject()
    val deleteFollow: DeleteFollow by inject()

    routing {
        follow(createFollow)
        unfollow(deleteFollow)
    }
}

private fun Application.configurePostRoutes(
    postController: PostController
) {
    val createPostUseCase: CreatePost by inject()

    routing {
        createPost(createPostUseCase)
        deletePost(postController)
        getPosts(postController)
    }
}

private fun Application.configureCommentRoutes(
    postController: PostController,
    activityController: ActivityController
) {
    val getCommentsUseCase: GetComments by inject()

    routing {
        createComment(activityController)
        deleteComment(postController)
        getComments(getCommentsUseCase)
    }
}

private fun Application.configureLikeRoutes(
    activityController: ActivityController
) {
    val deleteLikes: DeleteLikes by inject()

    routing {
        like(activityController)
        unlike(deleteLikes)
    }
}

private fun Application.configureActivityRoutes() {
    val getActivitiesUseCase: GetActivities by inject()

    routing {
        getUserActivity(getActivitiesUseCase)
    }
}
