package com.example.use_case.follow

import com.example.repository.follow.FollowRepository

class FindFollows(
    private val repository: FollowRepository
) {

    suspend operator fun invoke(followerId: String) =
        repository.findByFollowerId(followerId)

    suspend operator fun invoke(
        followerId: String,
        followedUserId: String
    ) = repository.findByOrderedIds(followerId, followedUserId)
}