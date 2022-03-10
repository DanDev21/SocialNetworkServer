package com.example.domain.service

import com.example.data.dto.request.like.LikeRequest
import com.example.data.dto.util.CrudResult.InsertResult
import com.example.domain.entity.Like
import com.example.data.repository.CommentRepository
import com.example.data.repository.LikeRepository
import com.example.data.repository.PostRepository
import com.example.data.repository.UserRepository
import com.example.data.validation.LikeValidator

class LikeService(
    private val likeRepository: LikeRepository,
    userRepository: UserRepository,
    postRepository: PostRepository,
    commentRepository: CommentRepository,
) {

    private val likeValidator =
        LikeValidator(
            findUser = userRepository::findById,
            findPost = postRepository::findById,
            findComment = commentRepository::findById
        )

    suspend fun add(
        request: LikeRequest,
        authorId: String,
    ) : InsertResult<Like> {
        val like = Like(
            authorId = authorId,
            targetId = request.targetId,
            targetInt = request.targetInt,
            timestamp = System.currentTimeMillis()
        )
        likeValidator.validate(like)
        return likeRepository.add(like)
    }

    suspend fun delete(
        likeId: String,
        authorId: String
    ) = likeRepository.delete(likeId, authorId)
}