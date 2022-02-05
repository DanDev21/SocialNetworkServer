package com.example.use_case.post

import com.example.domain.data.dto.request.post.DeletePostRequest
import com.example.repository.post.PostRepository

class DeletePost(
    private val repository: PostRepository
) {

    suspend operator fun invoke(
        request: DeletePostRequest,
        authorId: String,
    ) = repository.delete(request.postId, authorId)
}