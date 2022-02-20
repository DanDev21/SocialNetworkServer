package com.example.data.entity

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

data class Post(
    val authorId: String,

    val description: String?,
    val imageUrl: String,
    val timestamp: Long,

    val likesNo: Int = 0,
    val commentsNo: Int = 0,

    @BsonId
    val id: String = ObjectId().toString(),
)
