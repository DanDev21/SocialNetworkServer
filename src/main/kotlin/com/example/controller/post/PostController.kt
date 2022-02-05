package com.example.controller.post

import com.example.domain.data.dto.crud.CrudResult.FindManyResult
import com.example.domain.data.dto.request.comment.DeleteCommentRequest
import com.example.domain.data.dto.request.post.DeletePostRequest
import com.example.domain.model.Post
import com.example.use_case.comment.DeleteComments
import com.example.use_case.comment.GetComments
import com.example.use_case.follow.FindFollows
import com.example.use_case.like.DeleteLikes
import com.example.use_case.post.DeletePost
import com.example.use_case.post.GetPosts

class PostController(
    private val delete_post: DeletePost,
    private val delete_likes: DeleteLikes,
    private val delete_comments: DeleteComments,
    private val get_comments: GetComments,
    private val get_posts: GetPosts,
    private val find_follows: FindFollows,
) {

    suspend fun deletePost(
        request: DeletePostRequest,
        authorId: String,
    ) = delete_post(request, authorId).also {
        if (it.didSomething()) {
            delete_likes(request.postId)
            get_comments(request.postId)
                .items
                .forEach { comment ->  delete_likes(comment.id) }
            delete_comments(request.postId)
        }
    }

    suspend fun deleteComment(
        request: DeleteCommentRequest,
        authorId: String,
    ) = delete_comments(request, authorId).also {
        if (it.didSomething()) {
            delete_likes(request.commentId)
        }
    }

    suspend fun getPosts(
        pageNumber: Int,
        pageSize: Int,
        followerId: String
    ): FindManyResult<Post> {
        val usersIds = find_follows(followerId)
            .items.map { it.followedUserId }
        return get_posts(
            pageNumber = pageNumber,
            pageSize = pageSize,
            followedUsersIds = usersIds
        )
    }
}