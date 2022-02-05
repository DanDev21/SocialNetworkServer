package com.example.use_case.activity

import com.example.domain.data.dto.crud.CrudResult
import com.example.domain.model.Activity
import com.example.repository.activity.ActivityRepository
import com.example.repository.comment.CommentRepository
import com.example.repository.follow.FollowRepository
import com.example.repository.like.LikeRepository
import com.example.repository.user.UserRepository
import com.example.validation.ActivityValidator

class CreateActivity(
    private val repository: ActivityRepository,
    userRepository: UserRepository,
    likeRepository: LikeRepository,
    followRepository: FollowRepository,
    commentRepository: CommentRepository,
) {

    private val validator = ActivityValidator(
        findUser = userRepository::findById,
        findLike = likeRepository::findById,
        findComment = commentRepository::findById,
        findFollow = followRepository::findById
    )

    suspend operator fun invoke(
        authorId: String,
        targetId: String,
        targetUserId: String,
        actionInt: Int
    ): CrudResult.InsertResult<Activity> {
        val activity = Activity(
            authorId = authorId,
            targetId = targetId,
            targetUserId = targetUserId,
            actionInt = actionInt,
            timestamp = System.currentTimeMillis()
        )
        validator.validate(activity)
        return repository.add(activity)
    }
}