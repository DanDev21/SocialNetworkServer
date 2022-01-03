package com.example.domain.model

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

data class Like(
    val userId: String,
    val targetId: String,
    val targetType: Int,
    val timestamp: Long,
    @BsonId
    val id: String = ObjectId().toString()
)
