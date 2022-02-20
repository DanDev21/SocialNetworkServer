package com.example.data.repository.comment

import com.example.data.dto.util.CrudResult.*
import com.example.data.entity.Comment

interface CommentRepository {

    suspend fun add(comment: Comment): InsertResult<Comment>

    suspend fun findById(id: String): FindResult<Comment>

    suspend fun delete(id: String, authorId: String): DeleteResult<Comment>

    suspend fun delete(postId: String)

    suspend fun getAll(postId: String): FindManyResult<Comment>
}