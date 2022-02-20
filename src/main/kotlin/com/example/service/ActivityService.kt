package com.example.service

import com.example.data.dto.util.CrudResult.InsertResult
import com.example.data.dto.request.comment.CommentRequest
import com.example.data.dto.request.follow.FollowRequest
import com.example.data.dto.request.like.LikeRequest
import com.example.data.entity.Activity
import com.example.core.util.Action
import com.example.core.util.Target
import com.example.data.util.Validation
import com.example.data.repository.activity.ActivityRepository
import com.example.data.repository.comment.CommentRepository
import com.example.data.repository.like.LikeRepository
import com.example.data.repository.post.PostRepository
import com.example.data.repository.user.UserRepository
import com.example.data.validation.ActivityValidator
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ActivityService(
    userRepository: UserRepository,
    likeRepository: LikeRepository,
) : KoinComponent {

    private val activityRepository: ActivityRepository by inject()
    private val postRepository: PostRepository by inject()
    private val commentRepository: CommentRepository by inject()

    private val activityValidator =
        ActivityValidator(
            findUser = userRepository::findById,
            findLike = likeRepository::findById,
            findComment = commentRepository::findById,
        )

    suspend fun add(
        request: LikeRequest,
        authorId: String
    ): InsertResult<Activity> {
        val targetedUserId =
            getAuthorIdFor(request)
        return add(
            authorId = authorId,
            targetId = request.targetId,
            targetedUserId = targetedUserId,
            actionInt = Action.COMMENTED
        )
    }

    private suspend fun getAuthorIdFor(request: LikeRequest) =
        when (request.targetInt) {
            Target.POST -> postRepository.findById(request.targetId).item.authorId
            Target.COMMENT -> commentRepository.findById(request.targetId).item.authorId
            else -> throw Exception(Validation.TARGET_INT)
        }

    suspend fun add(
        request: CommentRequest,
        authorId: String,
    ): InsertResult<Activity> {
        val targetedUserId =
            postRepository.findById(request.postId).item.authorId
        return add(
            authorId = authorId,
            targetId = request.postId,
            targetedUserId = targetedUserId,
            actionInt = Action.COMMENTED
        )
    }

    suspend fun add(
        request: FollowRequest,
        followerId: String,
    ): InsertResult<Activity> {
        return add(
            authorId = followerId,
            targetId = followerId,
            targetedUserId = request.followedUserId,
            actionInt = Action.STARTED_FOLLOWING
        )
    }

    private suspend fun add(
        authorId: String,
        targetId: String,
        targetedUserId: String,
        actionInt: Int
    ): InsertResult<Activity> {
        val activity = Activity(
            authorId = authorId,
            targetId = targetId,
            targetUserId = targetedUserId,
            actionInt = actionInt,
            timestamp = System.currentTimeMillis()
        )
        activityValidator.validate(activity)
        return activityRepository.add(activity)
    }

    suspend fun getUserActivities(
        pageNumber: Int,
        pageSize: Int,
        targetedUserId: String
    ) = activityRepository.getAll(
        pageNumber = pageNumber,
        pageSize = pageSize,
        targetedUserId = targetedUserId
    )
}