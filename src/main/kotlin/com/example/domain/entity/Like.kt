package com.example.domain.entity

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

data class Like(
    val targetInt: Int,
    val authorId: String,
    val targetId: String,
    val timestamp: Long,
    @BsonId
    val id: String = ObjectId().toString(),
)
