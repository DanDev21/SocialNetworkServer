package com.example.service.post

import com.example.domain.util.AppException
import com.example.domain.validation.PostValidator
import com.example.repository.follow.FollowRepository
import com.example.repository.post.PostRepository
import com.example.repository.user.UserRepository

interface PostService {

    val postRepository: PostRepository
    val userRepository: UserRepository
    val followRepository: FollowRepository

    val postValidator: PostValidator

    @Throws(AppException::class)
    suspend fun add(
        authorId: String,
        description: String?,
        imageUrl: String
    )
}