package com.example.use_case.follow

import com.example.domain.data.dto.crud.CrudResult.InsertResult
import com.example.domain.data.dto.request.follow.FollowRequest
import com.example.domain.model.Follow
import com.example.repository.follow.FollowRepository
import com.example.repository.user.UserRepository
import com.example.validation.FollowValidator

class CreateFollow(
    private val repository: FollowRepository,
    userRepository: UserRepository
) {

    private val validator = FollowValidator(userRepository::findById)

    suspend operator fun invoke(
        request: FollowRequest,
        followerId: String,
    ): InsertResult<Follow> {
        val follow = Follow(
            followerId = followerId,
            followedUserId = request.followedUserId,
            timestamp = System.currentTimeMillis()
        )
        validator.validate(follow)
        return repository.add(follow)
    }
}