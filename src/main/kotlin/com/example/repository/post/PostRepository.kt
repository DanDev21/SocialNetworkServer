package com.example.repository.post

import com.example.domain.data.dto.crud.CrudResult.*
import com.example.domain.model.Post
import com.example.domain.util.Length

interface PostRepository {

    suspend fun add(post: Post): InsertResult<Post>

    suspend fun findById(id: String): FindResult<Post>

    suspend fun delete(postId: String, authorId: String): DeleteResult<Post>

    suspend fun getAll(
        pageNumber: Int = 0,
        pageSize: Int = Length.POST_PAGE,
        followedUsersIds: List<String>,
    ): FindManyResult<Post>

    suspend fun getAll(
        pageNumber: Int = 0,
        pageSize: Int = Length.POST_PAGE,
        authorId: String,
    ): FindManyResult<Post>
}