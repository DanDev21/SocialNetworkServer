package com.example.use_case.post

import com.example.repository.post.PostRepository

class FindPost(
    private val repository: PostRepository
) {

    suspend operator fun invoke(id: String) =
        repository.findById(id)
}