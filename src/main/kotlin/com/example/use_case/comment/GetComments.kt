package com.example.use_case.comment

import com.example.repository.comment.CommentRepository

class GetComments(
    private val repository: CommentRepository
) {

    suspend operator fun invoke(postId: String) =
        repository.getAll(postId)
}