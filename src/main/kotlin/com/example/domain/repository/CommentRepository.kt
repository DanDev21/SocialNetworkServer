package com.example.domain.repository

import com.example.data.dto.util.CrudResult.*
import com.example.domain.entity.Comment
import com.example.data.repository.CommentRepository
import org.litote.kmongo.and
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq

class CommentRepository(
    database: CoroutineDatabase
) : CommentRepository {

    private val comments = database.getCollection<Comment>()

    override suspend fun add(comment: Comment) = InsertResult(
        data = if (comments.insertOne(comment).wasAcknowledged()) comment else null
    )

    override suspend fun findById(id: String) = FindResult(
        data = comments.findOneById(id)
    )

    override suspend fun delete(
        id: String,
        authorId: String
    ) = DeleteResult(
        data = comments.findOneAndDelete(
            and(
                Comment::id eq id,
                Comment::authorId eq authorId
            )
        )
    )

    override suspend fun delete(postId: String) {
        comments.deleteMany(Comment::postId eq postId)
    }

    override suspend fun getAll(postId: String) = FindResult(
        data = comments.find(Comment::postId eq postId).toList()
    )
}