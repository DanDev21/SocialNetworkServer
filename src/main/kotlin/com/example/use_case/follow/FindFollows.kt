package com.example.use_case.follow

import com.example.repository.follow.FollowRepository

class FindFollows(
    private val repository: FollowRepository
) {

    suspend operator fun invoke(followerId: String) =
        repository.findByFollowerId(followerId)
}