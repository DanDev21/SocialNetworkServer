package com.example.domain.service

import com.example.data.dto.request.comment.CommentRequest
import com.example.data.dto.util.CrudResult
import com.example.data.dto.util.CrudResult.InsertResult
import com.example.data.dto.util.LightUser
import com.example.domain.entity.Comment
import com.example.data.repository.CommentRepository
import com.example.data.repository.LikeRepository
import com.example.data.repository.PostRepository
import com.example.data.repository.UserRepository
import com.example.data.validation.CommentValidator

class CommentService(
    private val commentRepository: CommentRepository,
    private val likeRepository: LikeRepository,
    userRepository: UserRepository,
    postRepository: PostRepository,
) {

    private val commentValidator =
        CommentValidator(
            findUser = userRepository::findById,
            findPost = postRepository::findById
        )

    suspend fun add(
        request: CommentRequest,
        authorId: String
    ): InsertResult<Comment> {
        val comment = Comment(
            body = request.body,
            postId = request.postId,
            authorId = authorId,
            timestamp = System.currentTimeMillis(),
            likes = 0,
            author = LightUser(
                username = request.username,
                profileImageUrl = request.userProfileImageUrl
            )
        )
        commentValidator.validate(comment)
        return commentRepository.add(comment)
    }

    suspend fun delete(
        commentId: String,
        authorId: String,
    ): CrudResult.DeleteResult<Comment> {
        val result = commentRepository.delete(
            id = commentId,
            authorId = authorId
        )

        if (result.succeeded) {
            likeRepository.delete(commentId)
        }
        return result
    }

    suspend fun getPostComments(postId: String) =
        commentRepository.getAll(postId)
}