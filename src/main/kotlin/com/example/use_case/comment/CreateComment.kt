package com.example.use_case.comment

import com.example.domain.data.dto.crud.CrudResult.InsertResult
import com.example.domain.data.dto.request.comment.CommentRequest
import com.example.domain.model.Comment
import com.example.domain.model.SimpleUser
import com.example.repository.comment.CommentRepository
import com.example.repository.post.PostRepository
import com.example.repository.user.UserRepository
import com.example.validation.CommentValidator

class CreateComment(
    private val repository: CommentRepository,
    userRepository: UserRepository,
    postRepository: PostRepository,
) {

    private val validator = CommentValidator(
        findUser = userRepository::findById,
        findPost = postRepository::findById,
    )

    suspend operator fun invoke(
        request: CommentRequest,
        authorId: String
    ): InsertResult<Comment> {
        val comment = Comment(
            body = request.body.trim(),
            postId = request.postId,
            authorId = authorId,
            timestamp = System.currentTimeMillis(),
            likes = 0,
            author = SimpleUser(
                username = request.username,
                userProfileImageUrl = request.userProfileImageUrl
            )
        )
        validator.validate(comment)
        return repository.add(comment)
    }
}