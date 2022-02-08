package com.example.use_case.comment

import com.example.repository.comment.CommentRepository

class FindComments(
    private val repository: CommentRepository
) {

    suspend operator fun invoke(postId: String) =
        repository.getAll(postId)
}