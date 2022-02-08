package com.example.service

import com.example.domain.data.dto.crud.CrudResult.InsertResult
import com.example.domain.data.dto.request.like.LikeRequest
import com.example.domain.data.dto.request.like.UnlikeRequest
import com.example.domain.model.Like
import com.example.repository.comment.CommentRepository
import com.example.repository.like.LikeRepository
import com.example.repository.post.PostRepository
import com.example.repository.user.UserRepository
import com.example.validation.LikeValidator
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class LikeService(
    userRepository: UserRepository,
    postRepository: PostRepository,
    commentRepository: CommentRepository,
) : KoinComponent {

    private val likeRepository: LikeRepository by inject()

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
        request: UnlikeRequest,
        authorId: String
    ) = likeRepository.delete(request.likeId, authorId)
}