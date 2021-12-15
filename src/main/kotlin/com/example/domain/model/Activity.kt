package com.example.domain.model

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

data class Activity(
    val authorId: String,
    val actionCode: Int,
    val targetCode: Int,
    val targetId: String,
    @BsonId
    val id: String = ObjectId().toString()
) {
    sealed class Action(val code: Int) {
        object None: Action(-1)
        object Follow: Action(0)
        object Comment : Action(1)
        object LikePost : Action(2)
        object LikeComment : Action(3)

        operator fun invoke(code: Int): Action = when (code) {
            0 -> Follow
            1 -> Comment
            2 -> LikePost
            3 -> LikeComment
            else -> None
        }
    }

    sealed class Target(val code: Int) {
        object None : Target(-1)
        object User : Target(0)
        object Post : Target(1)
        object Comment : Target(2)

        operator fun invoke(code: Int): Target = when (code) {
            0 -> User
            1 -> Post
            2 -> Comment
            else -> None
        }
    }
}
