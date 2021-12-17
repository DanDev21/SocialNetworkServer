package com.example.repository.post

import com.example.domain.model.Post
import com.example.domain.util.Constants

interface PostRepository {

    suspend fun add(post: Post): Boolean

    suspend fun delete(id: String): Boolean

    suspend fun getAll(
        userId: String,
        friendsIds: List<String>,
        page: Int = 0,
        pageSize: Int = Constants.Length.POST_PAGE
    ): List<Post>
}