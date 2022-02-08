package com.example.use_case.post

import com.example.repository.post.PostRepository

class FindPosts(
    private val repository: PostRepository
) {

    suspend operator fun invoke(id: String) =
        repository.findById(id)

    suspend operator fun invoke(
        pageNumber: Int,
        pageSize: Int,
        followedUsersIds: List<String>
    ) = repository.getAll(
        pageNumber = pageNumber,
        pageSize = pageSize,
        followedUsersIds = followedUsersIds
    )

    suspend operator fun invoke(
        pageNumber: Int,
        pageSize: Int,
        authorId: String,
    ) = repository.getAll(
        pageNumber = pageNumber,
        pageSize = pageSize,
        authorId = authorId
    )
}