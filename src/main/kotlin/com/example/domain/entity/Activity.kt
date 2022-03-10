package com.example.domain.entity

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

data class Activity(
    val authorId: String,
    val targetedPostId: String? = null,
    val targetedUserId: String,
    val actionInt: Int,
    val timestamp: Long,
    @BsonId
    val id: String = ObjectId().toString()
)
