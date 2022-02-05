package com.example.use_case.comment

import com.example.repository.comment.CommentRepository

class FindComment(
    private val repository: CommentRepository
) {

    suspend operator fun invoke(id: String) =
        repository.findById(id)
}