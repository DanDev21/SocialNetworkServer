package com.example.use_case.comment

import com.example.domain.data.dto.request.comment.DeleteCommentRequest
import com.example.repository.comment.CommentRepository

class DeleteComments(
    private val repository: CommentRepository
) {

    suspend operator fun invoke(
        request: DeleteCommentRequest,
        authorId: String
    ) = repository.delete(
        id = request.commentId,
        authorId = authorId
    )

    suspend operator fun invoke(postId: String) =
        repository.delete(postId)
}