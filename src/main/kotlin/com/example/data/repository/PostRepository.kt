package com.example.data.repository

import com.example.data.dto.util.CrudResult.*
import com.example.domain.entity.Post
import com.example.Length

interface PostRepository {

    suspend fun add(post: Post): InsertResult<Post>

    suspend fun findById(id: String): FindResult<Post>

    suspend fun delete(postId: String, authorId: String): DeleteResult<Post>

    suspend fun getAll(
        pageNumber: Int = 0,
        pageSize: Int = Length.PAGINATED_RESOURCE_PAGE,
        followedUsersIds: List<String>,
    ): FindResult<List<Post>>

    suspend fun getAll(
        pageNumber: Int = 0,
        pageSize: Int = Length.PAGINATED_RESOURCE_PAGE,
        authorId: String,
    ): FindResult<List<Post>>
}