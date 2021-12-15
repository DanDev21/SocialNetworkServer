package com.example.domain.model

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

data class Post(
    val authorId: String,

    val timestamp: Long,
    val description: String?,
    val imageUrl: String,

    @BsonId
    val id: String = ObjectId().toString(),
)
