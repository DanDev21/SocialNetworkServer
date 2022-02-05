package com.example.controller.post

import com.example.domain.data.dto.request.comment.CommentRequest
import com.example.domain.data.dto.request.like.LikeRequest
import com.example.domain.util.Action
import com.example.domain.util.Target
import com.example.domain.util.Validation
import com.example.use_case.activity.CreateActivity
import com.example.use_case.comment.CreateComment
import com.example.use_case.comment.FindComment
import com.example.use_case.like.CreateLike
import com.example.use_case.post.FindPost

class ActivityController(
    private val create_like: CreateLike,
    private val create_comment: CreateComment,
    private val find_comment: FindComment,
    private val create_activity: CreateActivity,
    private val find_post: FindPost
) {

    suspend fun like(
        request: LikeRequest,
        authorId: String
    ) = create_like(request, authorId).also {
        val like = it.obj
        val targetUserId = when (like.targetInt) {
            Target.POST -> find_post(like.targetId).item.authorId
            Target.COMMENT -> find_comment(like.targetId).item.authorId
            else -> throw throw Exception(Validation.TARGET_INT)
        }
        create_activity(
            authorId = authorId,
            targetId = like.targetId,
            targetUserId = targetUserId,
            actionInt = Action.LIKED
        )
    }

    suspend fun comment(
        request: CommentRequest,
        authorId: String,
    ) = create_comment(request, authorId).also {
        val targetUserId = find_post(request.postId).item.authorId
        create_activity(
            authorId = authorId,
            targetId = request.postId,
            targetUserId = targetUserId,
            actionInt = Action.COMMENTED
        )
    }
}