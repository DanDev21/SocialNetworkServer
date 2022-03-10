package com.example.data.repository

import com.example.data.dto.util.CrudResult.*
import com.example.domain.entity.Like

interface LikeRepository {

    suspend fun add(like: Like): InsertResult<Like>

    suspend fun findById(id: String): FindResult<Like>

    suspend fun delete(id: String, authorId: String): DeleteResult<Like>

    suspend fun delete(targetId: String)

    suspend fun getAll(authorId: String): FindResult<List<Like>>
}