package com.example.domain.model

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

data class Follow(
    val byWhoId: String,
    val otherId: String,
    val timestamp: Long,
    @BsonId
    val id: String = ObjectId().toString()
)
