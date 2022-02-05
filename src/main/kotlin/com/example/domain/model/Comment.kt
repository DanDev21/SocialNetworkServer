package com.example.domain.model

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

data class Comment(
    val body: String,
    val postId: String,
    val author: SimpleUser,
    val authorId: String,
    val timestamp: Long,
    val likes: Int,
    @BsonId
    val id: String = ObjectId().toString()
)
