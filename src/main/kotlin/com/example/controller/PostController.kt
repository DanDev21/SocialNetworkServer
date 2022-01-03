package com.example.controller

import com.example.domain.data.dto.request.DeleteCommentRequest
import com.example.domain.data.dto.request.DeletePostRequest
import com.example.service.CommentService
import com.example.service.LikeService
import com.example.service.PostService

class PostController(
    private val postService: PostService,
    private val likeService: LikeService,
    private val commentService: CommentService
) {

    suspend fun deletePost(
        userId: String,
        request: DeletePostRequest
    ) = postService
        .delete(userId, request)
        .apply {
            if (this) {
                commentService
                    .getAll(request.postId)
                    .forEach {
                        likeService.deleteAll(it.id)
                        commentService.delete(it.postId)
                    }
                likeService
                    .deleteAll(request.postId)
            }
        }

    suspend fun deleteComment(
        userId: String,
        request: DeleteCommentRequest
    ) = commentService
        .delete(userId, request)
        .apply {
            if (this) {
                likeService.deleteAll(request.commentId)
            }
        }
}