package com.example.data.entity

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

data class Activity(
    val authorId: String,
    val targetId: String,
    val targetUserId: String,
    val actionInt: Int,
    val timestamp: Long,
    @BsonId
    val id: String = ObjectId().toString()
)
