package com.example.repository.post

import com.example.domain.model.Post
import org.litote.kmongo.`in`
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq

class PostRepositoryImpl(
    database: CoroutineDatabase
) : PostRepository {

    private val posts = database.getCollection<Post>()

    override suspend fun add(post: Post) =
        posts.insertOne(post).wasAcknowledged()

    override suspend fun delete(id: String) =
        posts.deleteOne(Post::id eq id)
            .deletedCount > 0

    override suspend fun getAll(
        userId: String,
        friendsIds: List<String>,
        page: Int,
        pageSize: Int
    ): List<Post> = posts.find(Post::authorId `in` friendsIds)
        .skip(page * pageSize)
        .limit(pageSize)
        .descendingSort(Post::timestamp)
        .toList()
}