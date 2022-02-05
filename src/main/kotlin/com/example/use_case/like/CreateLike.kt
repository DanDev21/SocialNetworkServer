package com.example.use_case.like

import com.example.domain.data.dto.crud.CrudResult.InsertResult
import com.example.domain.data.dto.request.like.LikeRequest
import com.example.domain.model.Like
import com.example.repository.comment.CommentRepository
import com.example.repository.like.LikeRepository
import com.example.repository.post.PostRepository
import com.example.repository.user.UserRepository
import com.example.validation.LikeValidator

class CreateLike(
    private val repository: LikeRepository,
    commentRepository: CommentRepository,
    userRepository: UserRepository,
    postRepository: PostRepository,
) {

    private val validator = LikeValidator(
        findUser = userRepository::findById,
        findPost = postRepository::findById,
        findComment = commentRepository::findById
    )

    suspend operator fun invoke(
        request: LikeRequest,
        authorId: String,
    ): InsertResult<Like> {
        val like = Like(
            authorId = authorId,
            targetId = request.targetId,
            targetInt = request.targetInt,
            timestamp = System.currentTimeMillis()
        )
        validator.validate(like)
        return repository.add(like)
    }
}