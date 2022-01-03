package com.example.repository.post

import com.example.domain.model.Post
import com.example.domain.util.Constants

interface PostRepository {

    suspend fun add(post: Post): Boolean

    suspend fun delete(postId: String, authorId: String): Boolean

    suspend fun findById(id: String): Post?

    suspend fun getFriendsPosts(
        friendsIds: List<String>,
        pageNumber: Int = 0,
        pageSize: Int = Constants.Length.POST_PAGE
    ): List<Post>
}