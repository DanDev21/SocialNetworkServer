package com.example.domain.model

import com.example.domain.data.dto.request.SignUpRequest
import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId

data class User(
    val email: String,
    val username: String,
    val password: String,

    val bio: Bio = Bio(),
    val contact: Contact = Contact(),
    val skills: List<String> = listOf(),

    @BsonId
    val id: String = ObjectId().toString(),
) {
    data class Bio(
        val text: String? = null,
        val profileImageUrl: String? = null
    )

    data class Contact(
        val github: String? = null,
        val linkedIn: String? = null,
        val instagram: String? = null
    )
}
