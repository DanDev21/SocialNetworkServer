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
import com.example.use_case.post.FindPosts

class ActivityController(
    private val uc_createLike: CreateLike,
    private val uc_createComment: CreateComment,
    private val uc_findComment: FindComment,
    private val uc_createActivity: CreateActivity,
    private val uc_findPosts: FindPosts
) {

    suspend fun like(
        request: LikeRequest,
        authorId: String
    ) = uc_createLike(request, authorId).also {
        val like = it.obj
        val targetUserId = when (like.targetInt) {
            Target.POST -> uc_findPosts(like.targetId).item.authorId
            Target.COMMENT -> uc_findComment(like.targetId).item.authorId
            else -> throw throw Exception(Validation.TARGET_INT)
        }
        uc_createActivity(
            authorId = authorId,
            targetId = like.targetId,
            targetUserId = targetUserId,
            actionInt = Action.LIKED
        )
    }

    suspend fun comment(
        request: CommentRequest,
        authorId: String,
    ) = uc_createComment(request, authorId).also {
        val targetUserId = uc_findPosts(request.postId).item.authorId
        uc_createActivity(
            authorId = authorId,
            targetId = request.postId,
            targetUserId = targetUserId,
            actionInt = Action.COMMENTED
        )
    }
}