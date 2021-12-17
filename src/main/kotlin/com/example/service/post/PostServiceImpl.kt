package com.example.service.post

import com.example.domain.model.Post
import com.example.domain.validation.PostValidator
import com.example.repository.follow.FollowRepository
import com.example.repository.post.PostRepository
import com.example.repository.user.UserRepository

class PostServiceImpl(
    override val postRepository: PostRepository,
    override val userRepository: UserRepository,
    override val followRepository: FollowRepository
) : PostService {

    override val postValidator = PostValidator().apply {
        findUserById = userRepository::findById
    }

    override suspend fun add(
        authorId: String,
        description: String?,
        imageUrl: String
    ) {
        val post = Post(
            authorId = authorId,
            description = description,
            imageUrl = imageUrl,
            timestamp = System.currentTimeMillis()
        )
        postValidator.validate(post)
        postRepository.add(post)
    }
}