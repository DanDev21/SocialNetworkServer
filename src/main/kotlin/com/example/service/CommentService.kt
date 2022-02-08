package com.example.service

import com.example.domain.data.dto.crud.CrudResult.InsertResult
import com.example.domain.data.dto.request.comment.CommentRequest
import com.example.domain.data.dto.request.comment.DeleteCommentRequest
import com.example.domain.model.Comment
import com.example.domain.model.SimpleUser
import com.example.repository.comment.CommentRepository
import com.example.repository.like.LikeRepository
import com.example.repository.post.PostRepository
import com.example.repository.user.UserRepository
import com.example.validation.CommentValidator
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class CommentService(
    userRepository: UserRepository,
    postRepository: PostRepository,
) : KoinComponent {

    private val commentRepository: CommentRepository by inject()
    private val likeRepository: LikeRepository by inject()

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
            author = SimpleUser(
                username = request.username,
                userProfileImageUrl = request.userProfileImageUrl
            )
        )
        commentValidator.validate(comment)
        return commentRepository.add(comment)
    }

    suspend fun delete(
        request: DeleteCommentRequest,
        authorId: String,
    ) = commentRepository.delete(
        id = request.commentId,
        authorId = authorId
    ).also {
        if (it.wasAcknowledged()) {
            likeRepository.delete(request.commentId)
        }
    }

    suspend fun getPostComments(postId: String) =
        commentRepository.getAll(postId)
}