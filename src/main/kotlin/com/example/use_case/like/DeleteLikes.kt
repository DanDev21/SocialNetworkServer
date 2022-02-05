package com.example.use_case.like

import com.example.domain.data.dto.request.like.UnlikeRequest
import com.example.repository.like.LikeRepository

class DeleteLikes(
    private val repository: LikeRepository
) {

    suspend operator fun invoke(
        request: UnlikeRequest,
        authorId: String
    ) = repository.delete(request.likeId, authorId)

    suspend operator fun invoke(targetId: String) =
        repository.delete(targetId)
}