package com.example.service

import com.example.domain.data.dto.request.LikeRequest
import com.example.domain.data.dto.request.UnlikeRequest
import com.example.domain.model.Like
import com.example.domain.validation.LikeValidator
import com.example.repository.comment.CommentRepository
import com.example.repository.like.LikeRepository
import com.example.repository.post.PostRepository
import com.example.repository.user.UserRepository

class LikeService(
    private val likeRepository: LikeRepository,
    userRepository: UserRepository,
    postRepository: PostRepository,
    commentRepository: CommentRepository
) {

    private val likeValidator = LikeValidator(
        findUserById = userRepository::findById,
        findPostById = postRepository::findById,
        findCommentById = commentRepository::findById
    )

    suspend fun add(
        userId: String,
        request: LikeRequest
    ) {
        val like = Like(
            userId = userId,
            targetId = request.targetId,
            targetType = request.targetType,
            timestamp = System.currentTimeMillis()
        )
        likeValidator.validate(like)
        likeRepository.add(like)
    }

    suspend fun delete(
        userId: String,
        request: UnlikeRequest
    ): Boolean = likeRepository
        .delete(request.likeId, userId)

    suspend fun deleteAll(targetId: String) =
        likeRepository.deleteAll(targetId)
}