package com.example.use_case.post

import com.example.repository.post.PostRepository

class GetPosts(
    private val repository: PostRepository
) {

    suspend operator fun invoke(
        pageNumber: Int,
        pageSize: Int,
        followedUsersIds: List<String>
    ) = repository.getAll(
        pageNumber = pageNumber,
        pageSize = pageSize,
        followedUsersIds = followedUsersIds
    )
}