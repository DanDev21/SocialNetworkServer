package com.example.service

import com.example.data.dto.util.CrudResult
import com.example.data.dto.util.CrudResult.InsertResult
import com.example.data.dto.request.post.CreatePostRequest
import com.example.data.dto.request.post.DeletePostRequest
import com.example.data.entity.Post
import com.example.data.repository.comment.CommentRepository
import com.example.data.repository.follow.FollowRepository
import com.example.data.repository.like.LikeRepository
import com.example.data.repository.post.PostRepository
import com.example.data.repository.user.UserRepository
import com.example.data.validation.PostValidator
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class PostService(
    userRepository: UserRepository,
) : KoinComponent {

    private val postRepository: PostRepository by inject()
    private val followRepository: FollowRepository by inject()
    private val commentRepository: CommentRepository by inject()
    private val likeRepository: LikeRepository by inject()

    private val postValidator =
        PostValidator(userRepository::findById)

    suspend fun add(
        request: CreatePostRequest,
        authorId: String,
    ): InsertResult<Post> {
        val post = Post(
            authorId = authorId,
            description = request.description,
            imageUrl = request.imageUrl,
            timestamp = System.currentTimeMillis()
        )
        postValidator.validate(post)
        return postRepository.add(post)
    }

    suspend fun findById(id: String) =
        postRepository.findById(id)

    suspend fun getFollowedPosts(
        pageNumber: Int,
        pageSize: Int,
        followerId: String,
    ): CrudResult.FindManyResult<Post> {
        val followedUsersIds =
            followRepository.findByFollowerId(followerId)
                .items.map { it.followedUserId }
        return postRepository.getAll(
            pageNumber = pageNumber,
            pageSize = pageSize,
            followedUsersIds = followedUsersIds
        )
    }

    suspend fun getUserPosts(
        pageNumber: Int,
        pageSize: Int,
        authorId: String,
    ) = postRepository.getAll(
        pageNumber = pageNumber,
        pageSize = pageSize,
        authorId = authorId
    )

    suspend fun delete(
        request: DeletePostRequest,
        authorId: String,
    ) = postRepository.delete(
        postId = request.postId,
        authorId = authorId
    ).also {
        if(it.wasAcknowledged()) {
            likeRepository.delete(request.postId)
            commentRepository.getAll(request.postId)
                .items.forEach { comment ->
                    likeRepository.delete(comment.id)
                }
            commentRepository.delete(request.postId)
        }
    }
}