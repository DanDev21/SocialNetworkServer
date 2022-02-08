package com.example.routes

import com.example.controller.post.PostController
import com.example.domain.data.dto.request.post.CreatePostRequest
import com.example.domain.data.dto.request.post.DeletePostRequest
import com.example.domain.util.AppException
import com.example.domain.util.Length
import com.example.domain.util.Routes
import com.example.domain.util.extensions.pageNumber
import com.example.domain.util.extensions.pageSize
import com.example.domain.util.extensions.receive
import com.example.domain.util.extensions.requesterId
import com.example.use_case.post.CreatePost
import com.example.use_case.post.FindPosts
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.routing.*

fun Route.createPost(
    createPost: CreatePost
) {
    authenticate {
        post(Routes.Post.CREATE_POST) {
            try {
                val request = call.receive<CreatePostRequest>()
                createPost(request, call.requesterId)
                TODO()
            } catch (e: AppException) {
                TODO()
            } catch (e: Exception) {
                e.printStackTrace()
                TODO()
            }
        }
    }
}

fun Route.deletePost(
    controller: PostController
) {
    authenticate {
        delete(Routes.Post.DELETE_POST) {
            try {
                val request = call.receive<DeletePostRequest>()
                val deleted = controller.deletePost(request, call.requesterId)
                TODO()
            } catch (e: AppException) {
                TODO()
            } catch (e: Exception) {
                e.printStackTrace()
                TODO()
            }
        }
    }
}

fun Route.getPosts(
    postController: PostController
) {
    authenticate {
        get(Routes.Post.GET_FOLLOWED_USERS_POSTS) {
            try {
                postController.getPosts(
                    pageNumber = call.pageNumber,
                    pageSize = call.pageSize ?: Length.POST_PAGE,
                    followerId = call.requesterId
                )
                TODO()
            } catch (e: AppException) {
                TODO()
            } catch (e: Exception) {
                e.printStackTrace()
                TODO()
            }
        }
    }
}

fun Route.getPosts(
    findPosts: FindPosts
) {
    authenticate {
        get(Routes.Post.GET_USER_POSTS) {
            try {
                val posts = findPosts(
                    pageNumber = call.pageNumber,
                    pageSize = call.pageSize ?: Length.POST_PAGE,
                    authorId = call.requesterId
                ).items
                TODO()
            } catch (e: AppException) {
                TODO()
            } catch (e: Exception) {
                e.printStackTrace()
                TODO()
            }
        }
    }
}
