package com.example.domain.entity

import com.example.data.dto.util.LightUser
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

data class Comment(
    val body: String,
    val postId: String,
    val author: LightUser,
    val authorId: String,
    val timestamp: Long,
    val likes: Int,
    @BsonId
    val id: String = ObjectId().toString()
)
