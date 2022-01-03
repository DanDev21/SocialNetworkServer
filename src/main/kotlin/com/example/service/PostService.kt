package com.example.service

import com.example.domain.model.Post
import com.example.domain.data.dto.request.CreatePostRequest
import com.example.domain.data.dto.request.DeletePostRequest
import com.example.domain.validation.PostValidator
import com.example.repository.post.PostRepository
import com.example.repository.user.UserRepository

class PostService(
    private val postRepository: PostRepository,
    userRepository: UserRepository
) {

    private val postValidator =
        PostValidator(userRepository::findById)

    suspend fun add(
        userId: String,
        request: CreatePostRequest
    ) {
        val post = Post(
            authorId = userId,
            description = request.description,
            imageUrl = request.imageUrl,
            timestamp = System.currentTimeMillis()
        )
        postValidator.validate(post)
        postRepository.add(post)
    }

    suspend fun delete(
        userId: String,
        request: DeletePostRequest
    ) = postRepository
        .delete(request.postId, userId)

    suspend fun getFriendsPosts(
        friendsIds: List<String>,
        pageNumber: Int,
        pageSize: Int,
    ) = postRepository.getFriendsPosts(
            friendsIds = friendsIds,
            pageNumber = pageNumber,
            pageSize = pageSize
        )
}