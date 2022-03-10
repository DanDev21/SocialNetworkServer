package com.example.domain.service

import com.example.Action
import com.example.Target
import com.example.data.dto.request.PaginatedResourceRequest
import com.example.data.dto.request.comment.CommentRequest
import com.example.data.dto.request.like.LikeRequest
import com.example.data.dto.util.CrudResult.InsertResult
import com.example.data.repository.ActivityRepository
import com.example.data.repository.CommentRepository
import com.example.data.repository.PostRepository
import com.example.data.repository.UserRepository
import com.example.data.validation.ActivityValidator
import com.example.domain.entity.Activity
import com.example.util.AppException.InvalidException
import com.example.util.AppException.InvalidException.Validation

class ActivityService(
    private val activityRepository: ActivityRepository,
    private val postRepository: PostRepository,
    private val commentRepository: CommentRepository,
    userRepository: UserRepository,
) {

    private val activityValidator =
        ActivityValidator(
            findUser = userRepository::findById,
            findPost = postRepository::findById,
        )

    suspend fun add(
        request: LikeRequest,
        authorId: String,
    ): InsertResult<Activity> {
        val targetedUserId =
            when (request.targetInt) {
                Target.POST -> {
                    postRepository.findById(request.targetId).content.authorId
                }
                Target.COMMENT -> {
                    commentRepository.findById(request.targetId).content.authorId
                }
                else -> throw InvalidException(Validation.TARGET_INT)
            }

        val targetedPostId =
            when (request.targetInt) {
                Target.POST -> request.targetId

                Target.COMMENT -> {
                    commentRepository.findById(request.targetId).content.postId
                }

                else -> throw Exception(Validation.TARGET_ID)
            }

        return add(
            authorId = authorId,
            targetedPostId = targetedPostId,
            targetedUserId = targetedUserId,
            actionInt = Action.LIKED
        )
    }

    suspend fun add(
        request: CommentRequest,
        authorId: String,
    ): InsertResult<Activity> {
        val targetedUserId = postRepository
            .findById(request.postId).content.authorId

        return add(
            authorId = authorId,
            targetedPostId = request.postId,
            targetedUserId = targetedUserId,
            actionInt = Action.COMMENTED
        )
    }

    suspend fun add(
        followedUserId: String,
        followerId: String,
    ) = add(
        authorId = followerId,
        targetedUserId = followedUserId,
        actionInt = Action.STARTED_FOLLOWING
    )

    private suspend fun add(
        authorId: String,
        targetedPostId: String? = null,
        targetedUserId: String,
        actionInt: Int
    ): InsertResult<Activity> {
        val activity = Activity(
            authorId = authorId,
            targetedPostId = targetedPostId,
            targetedUserId = targetedUserId,
            actionInt = actionInt,
            timestamp = System.currentTimeMillis()
        )
        activityValidator.validate(activity)
        return activityRepository.add(activity)
    }

    suspend fun getUserActivities(
        request: PaginatedResourceRequest,
        targetedUserId: String
    ) = activityRepository.getAll(
        pageNumber = request.pageNumber,
        pageSize = request.pageSize,
        targetedUserId = targetedUserId
    )
}