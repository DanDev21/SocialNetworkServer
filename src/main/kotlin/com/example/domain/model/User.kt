package com.example.domain.model

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

data class User(
    val email: String,
    val username: String,
    val password: String,

    val bio: String? = null,
    val profileImageUrl: String? = null,

    val github: String? = null,
    val linkedIn: String? = null,
    val instagram: String? = null,

    val skills: List<String> = listOf(),

    val followersNo: Int = 0,
    val followedUsersNo: Int = 0,
    val postsNo: Int = 0,

    @BsonId
    val id: String = ObjectId().toString(),
)