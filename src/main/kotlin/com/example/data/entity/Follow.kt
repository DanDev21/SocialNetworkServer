package com.example.data.entity

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

data class Follow(
    val followerId: String,
    val followedUserId: String,
    val timestamp: Long,
    @BsonId
    val id: String = ObjectId().toString()
)