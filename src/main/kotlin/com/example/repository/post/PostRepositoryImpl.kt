package com.example.repository.post

import com.example.domain.model.Post
import org.litote.kmongo.`in`
import org.litote.kmongo.and
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq

class PostRepositoryImpl(
    database: CoroutineDatabase
) : PostRepository {

    private val posts = database.getCollection<Post>()

    override suspend fun add(post: Post) =
        posts.insertOne(post).wasAcknowledged()

    override suspend fun delete(postId: String, authorId: String) =
        posts.deleteOne(
            and(
                Post::id eq postId,
                Post::authorId eq authorId
            )
        ).deletedCount > 0

    override suspend fun findById(id: String) =
        posts.findOne(Post::id eq id)

    override suspend fun getFriendsPosts(
        friendsIds: List<String>,
        pageNumber: Int,
        pageSize: Int
    ): List<Post> =
        posts.find(Post::authorId `in` friendsIds)
            .skip(pageNumber * pageSize)
            .limit(pageSize)
            .descendingSort(Post::timestamp)
            .toList()
}