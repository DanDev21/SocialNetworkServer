package com.example.repository.like

import com.example.domain.data.dto.crud.CrudResult.*
import com.example.domain.model.Like

interface LikeRepository {

    suspend fun add(like: Like): InsertResult<Like>

    suspend fun findById(id: String): FindResult<Like>

    suspend fun delete(id: String, authorId: String): DeleteResult<Like>

    suspend fun delete(targetId: String)

    suspend fun getAll(authorId: String): FindManyResult<Like>
}