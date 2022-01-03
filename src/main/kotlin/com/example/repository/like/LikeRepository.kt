package com.example.repository.like

import com.example.domain.model.Like

interface LikeRepository {

    suspend fun add(like: Like): Boolean

    suspend fun delete(id: String, authorId: String): Boolean

    suspend fun deleteAll(targetId: String)

    suspend fun getAll(userId: String): List<Like>
}