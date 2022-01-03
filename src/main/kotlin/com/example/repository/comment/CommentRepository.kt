package com.example.repository.comment

import com.example.domain.model.Comment

interface CommentRepository {

    suspend fun add(comment: Comment)

    suspend fun findById(id: String): Comment?

    suspend fun delete(id: String, authorId: String): Boolean

    suspend fun delete(postId: String)

    suspend fun getAll(postId: String): List<Comment>
}