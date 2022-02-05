package com.example.use_case.post

import com.example.domain.data.dto.crud.CrudResult.InsertResult
import com.example.domain.data.dto.request.post.CreatePostRequest
import com.example.domain.model.Post
import com.example.repository.post.PostRepository
import com.example.repository.user.UserRepository
import com.example.validation.PostValidator

class CreatePost(
    private val repository: PostRepository,
    userRepository: UserRepository
) {

    private val validator = PostValidator(userRepository::findById)

    suspend operator fun invoke(
        request: CreatePostRequest,
        authorId: String,
    ): InsertResult<Post> {
        val post = Post(
            authorId = authorId,
            description = request.description,
            imageUrl = request.imageUrl,
            timestamp = System.currentTimeMillis()
        )
        validator.validate(post)
        return repository.add(post)
    }
}