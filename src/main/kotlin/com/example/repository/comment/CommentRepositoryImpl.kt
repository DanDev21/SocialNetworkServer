package com.example.repository.comment

import com.example.domain.model.Comment
import org.litote.kmongo.and
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq

class CommentRepositoryImpl(
    database: CoroutineDatabase
) : CommentRepository {

    private val comments = database.getCollection<Comment>()

    override suspend fun add(comment: Comment) {
        comments.insertOne(comment)
    }

    override suspend fun findById(id: String) =
        comments.findOne(Comment::id eq id)

    override suspend fun delete(
        id: String,
        authorId: String
    ): Boolean {
        return comments.deleteOne(
            and(
                Comment::id eq id,
                Comment::authorId eq authorId
            )
        ).deletedCount > 0
    }

    override suspend fun delete(postId: String) {
        comments.deleteOne(Comment::postId eq postId)
    }

    override suspend fun getAll(postId: String) =
        comments.find(Comment::postId eq postId).toList()
}