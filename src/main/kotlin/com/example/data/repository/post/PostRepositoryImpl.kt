package com.example.data.repository.post

import com.example.data.dto.util.CrudResult.*
import com.example.data.entity.Post
import org.litote.kmongo.`in`
import org.litote.kmongo.and
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq

class PostRepositoryImpl(
    database: CoroutineDatabase
) : PostRepository {

    private val posts = database.getCollection<Post>()

    override suspend fun add(post: Post) = InsertResult(
        succeeded = posts.insertOne(post).wasAcknowledged(),
        obj = post
    )

    override suspend fun findById(id: String) = FindResult(
        obj = posts.findOneById(id)
    )

    override suspend fun delete(
        postId: String,
        authorId: String
    ) = DeleteResult<Post>(
        deleteCount = posts.deleteOne(
            and(
                Post::id eq postId,
                Post::authorId eq authorId
            )
        ).deletedCount
    )

    override suspend fun getAll(
        pageNumber: Int,
        pageSize: Int,
        followedUsersIds: List<String>
    ) = FindManyResult(
        items = posts.find(Post::authorId `in` followedUsersIds)
            .skip(pageNumber * pageSize)
            .limit(pageSize)
            .descendingSort(Post::timestamp)
            .toList()
    )

    override suspend fun getAll(
        pageNumber: Int,
        pageSize: Int,
        authorId: String
    ) = FindManyResult(
        items = posts.find(Post::authorId eq authorId)
            .skip(pageNumber * pageSize)
            .limit(pageSize)
            .descendingSort(Post::timestamp)
            .toList()
    )
}