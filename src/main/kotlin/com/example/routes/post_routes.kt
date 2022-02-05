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
import com.example.domain.util.extensions.userId
import com.example.use_case.post.CreatePost
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
                createPost(request, call.userId)
                // TODO: send response
            } catch (e: AppException) {
                // TODO: send response
            } catch (e: Exception) {
                e.printStackTrace()
                // TODO: send response
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
                val deleted = controller.deletePost(request, call.userId)
                // TODO: send response
            } catch (e: AppException) {
                // TODO: send response
            } catch (e: Exception) {
                e.printStackTrace()
                // TODO: send response
            }
        }
    }
}

fun Route.getPosts(
    postController: PostController
) {
    authenticate {
        get(Routes.Post.GET_FRIENDS_POSTS) {
            try {
                postController.getPosts(
                    pageNumber = call.pageNumber,
                    pageSize = call.pageSize ?: Length.POST_PAGE,
                    followerId = call.userId
                )
                // TODO: send the posts
            } catch (e: AppException) {
                // TODO: send response
            } catch (e: Exception) {
                e.printStackTrace()
                // TODO: send response
            }
        }
    }
}
