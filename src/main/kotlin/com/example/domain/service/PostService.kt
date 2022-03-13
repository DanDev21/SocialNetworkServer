package com.example.domain.service

import com.example.util.Folder
import com.example.util.Url
import com.example.data.dto.util.DataWrapper
import com.example.util.FileManager
import com.example.data.dto.util.CrudResult
import com.example.data.dto.util.CrudResult.InsertResult
import com.example.data.dto.util.Tuple
import com.example.domain.entity.Post
import com.example.data.repository.CommentRepository
import com.example.data.repository.FollowRepository
import com.example.data.repository.LikeRepository
import com.example.data.repository.PostRepository
import com.example.data.validation.PostValidator

class PostService(
    private val postRepository: PostRepository,
    private val followRepository: FollowRepository,
    private val commentRepository: CommentRepository,
    private val likeRepository: LikeRepository,
) {

    private val postValidator = PostValidator()
    private val folderManager =
        FileManager.getNewManagerFor(Folder.POST_IMAGES, Url.POST_IMAGES)

    suspend fun add(
        tuple: Tuple<DataWrapper<String>, FileManager.RawFileData>,
        authorId: String,
    ): InsertResult<Post> {
        val post = Post(
            authorId = authorId,
            description = tuple.first.data,
            imageUrl = Url.POST_IMAGES + tuple.second.fileName,
            timestamp = System.currentTimeMillis()
        )
        postValidator.validate(post)
        folderManager.save(tuple.second)

        val result = postRepository.add(post)
        if (result.failed) {
            folderManager.delete(tuple.second.fileName)
        }
        return result
    }

    suspend fun findById(id: String) =
        postRepository.findById(id)

    suspend fun getFollowedPosts(
        pageNumber: Int,
        pageSize: Int,
        followerId: String,
    ): CrudResult.FindResult<List<Post>> {
        val followedUsersIds = followRepository.findByFollowerId(followerId)
                .content.map { it.followedUserId }

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
        postId: String,
        authorId: String,
    ): CrudResult.DeleteResult<Post> {
        val result = postRepository.delete(
            postId = postId,
            authorId = authorId
        )

        if (result.succeeded) {
            folderManager.deleteUsingUrl(result.content.imageUrl)

            likeRepository.delete(postId)
            commentRepository.getAll(postId)
                .content.forEach { comment ->
                    likeRepository.delete(comment.id)
                }
            commentRepository.delete(postId)
        }

        return result
    }
}