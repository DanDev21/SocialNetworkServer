package com.example.repository.comment

import com.example.domain.data.dto.crud.CrudResult.*
import com.example.domain.model.Comment

interface CommentRepository {

    suspend fun add(comment: Comment): InsertResult<Comment>

    suspend fun findById(id: String): FindResult<Comment>

    suspend fun delete(id: String, authorId: String): DeleteResult<Comment>

    suspend fun delete(postId: String)

    suspend fun getAll(postId: String): FindManyResult<Comment>
}