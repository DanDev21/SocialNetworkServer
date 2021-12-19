package com.example.service

import com.example.domain.model.Post
import com.example.domain.data.dto.request.CreatePostRequest
import com.example.domain.validation.PostValidator
import com.example.repository.follow.FollowRepository
import com.example.repository.post.PostRepository
import com.example.repository.user.UserRepository

class PostService(
    private val postRepository: PostRepository,
    private val followRepository: FollowRepository,
    private val userRepository: UserRepository
) {

    private val postValidator = PostValidator(userRepository::findById)

    suspend fun add(request: CreatePostRequest) {
        val post = Post(
            authorId = request.authorId,
            description = request.description,
            imageUrl = request.imageUrl,
            timestamp = System.currentTimeMillis()
        )
        postValidator.validate(post)
        postRepository.add(post)
    }
}