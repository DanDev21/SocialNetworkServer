package com.example.service

import com.example.data.dto.util.CrudResult.InsertResult
import com.example.data.dto.request.like.LikeRequest
import com.example.data.dto.request.like.UnlikeRequest
import com.example.data.entity.Like
import com.example.data.repository.comment.CommentRepository
import com.example.data.repository.like.LikeRepository
import com.example.data.repository.post.PostRepository
import com.example.data.repository.user.UserRepository
import com.example.data.validation.LikeValidator
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