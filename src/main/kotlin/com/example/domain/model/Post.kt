package com.example.domain.model

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

data class Post(
    val authorId: String,

    val description: String?,
    val imageUrl: String,
    val timestamp: Long,

    val statistics: Statistics = Statistics(),

    @BsonId
    val id: String = ObjectId().toString(),
) {

    data class Statistics(
        val likesNo: Int = 0,
        val commentsNo: Int = 0,
    )
}
