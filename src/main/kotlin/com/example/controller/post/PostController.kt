package com.example.controller.post

import com.example.domain.data.dto.crud.CrudResult.FindManyResult
import com.example.domain.data.dto.request.comment.DeleteCommentRequest
import com.example.domain.data.dto.request.post.DeletePostRequest
import com.example.domain.model.Post
import com.example.use_case.comment.DeleteComments
import com.example.use_case.comment.FindComments
import com.example.use_case.follow.FindFollows
import com.example.use_case.like.DeleteLikes
import com.example.use_case.post.DeletePost
import com.example.use_case.post.FindPosts

class PostController(
    private val uc_deletePost: DeletePost,
    private val uc_deleteLikes: DeleteLikes,
    private val uc_deleteComments: DeleteComments,
    private val uc_findComments: FindComments,
    private val uc_findPosts: FindPosts,
    private val uc_findFollows: FindFollows,
) {

    suspend fun deletePost(
        request: DeletePostRequest,
        authorId: String,
    ) = uc_deletePost(request, authorId).also {
        if (it.wasAcknowledged()) {
            uc_deleteLikes(request.postId)
            uc_findComments(request.postId)
                .items
                .forEach { comment ->  uc_deleteLikes(comment.id) }
            uc_deleteComments(request.postId)
        }
    }

    suspend fun deleteComment(
        request: DeleteCommentRequest,
        authorId: String,
    ) = uc_deleteComments(request, authorId).also {
        if (it.wasAcknowledged()) {
            uc_deleteLikes(request.commentId)
        }
    }

    suspend fun getPosts(
        pageNumber: Int,
        pageSize: Int,
        followerId: String
    ): FindManyResult<Post> {
        val usersIds = uc_findFollows(followerId)
            .items.map { it.followedUserId }
        return uc_findPosts(
            pageNumber = pageNumber,
            pageSize = pageSize,
            followedUsersIds = usersIds
        )
    }
}