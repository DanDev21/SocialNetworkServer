package com.example.service

import com.example.domain.data.dto.request.CommentRequest
import com.example.domain.data.dto.request.DeleteCommentRequest
import com.example.domain.model.Comment
import com.example.domain.model.SimpleUser
import com.example.domain.validation.CommentValidator
import com.example.repository.comment.CommentRepository
import com.example.repository.post.PostRepository
import com.example.repository.user.UserRepository

class CommentService(
    private val commentRepository: CommentRepository,
    userRepository: UserRepository,
    postRepository: PostRepository,
) {

    private val commentValidator = CommentValidator(
        findUserById = userRepository::findById,
        findPostById = postRepository::findById
    )

    suspend fun add(userId: String, request: CommentRequest) {
        val comment = Comment(
            body = request.body.trim(),
            postId = request.postId,
            authorId = userId,
            timestamp = System.currentTimeMillis(),
            likes = 0,
            simpleUser = SimpleUser(
                username = request.username,
                userProfileImageUrl = request.userProfileImageUrl
            )
        )
        commentValidator.validate(comment)
        commentRepository.add(comment)
    }

    suspend fun delete(
        userId: String,
        request: DeleteCommentRequest
    ) =
        commentRepository.delete(
            id = request.commentId,
            authorId = userId
        )

    suspend fun delete(postId: String) =
        commentRepository.delete(postId)

    suspend fun getAll(postId: String) =
        commentRepository.getAll(postId)
}