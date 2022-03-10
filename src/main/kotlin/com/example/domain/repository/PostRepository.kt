package com.example.domain.repository

import com.example.data.dto.util.CrudResult.*
import com.example.domain.entity.Post
import com.example.data.repository.PostRepository
import org.litote.kmongo.`in`
import org.litote.kmongo.and
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq

class PostRepository(
    database: CoroutineDatabase
) : PostRepository {

    private val posts = database.getCollection<Post>()

    override suspend fun add(post: Post) = InsertResult(
        data = if (posts.insertOne(post).wasAcknowledged()) post else null
    )

    override suspend fun findById(id: String) = FindResult(
        data = posts.findOneById(id)
    )

    override suspend fun delete(
        postId: String,
        authorId: String
    ) = DeleteResult(
        data = posts.findOneAndDelete(
            and(
                Post::id eq postId,
                Post::authorId eq authorId
            )
        )
    )

    override suspend fun getAll(
        pageNumber: Int,
        pageSize: Int,
        followedUsersIds: List<String>
    ) = FindResult(
        data = posts.find(Post::authorId `in` followedUsersIds)
            .skip(pageNumber * pageSize)
            .limit(pageSize)
            .descendingSort(Post::timestamp)
            .toList()
    )

    override suspend fun getAll(
        pageNumber: Int,
        pageSize: Int,
        authorId: String
    ) = FindResult(
        data = posts.find(Post::authorId eq authorId)
            .skip(pageNumber * pageSize)
            .limit(pageSize)
            .descendingSort(Post::timestamp)
            .toList()
    )
}