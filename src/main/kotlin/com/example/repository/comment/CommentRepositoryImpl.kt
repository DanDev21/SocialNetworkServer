package com.example.repository.comment

import com.example.domain.data.dto.crud.CrudResult.*
import com.example.domain.model.Comment
import org.litote.kmongo.and
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq

class CommentRepositoryImpl(
    database: CoroutineDatabase
) : CommentRepository {

    private val comments = database.getCollection<Comment>()

    override suspend fun add(comment: Comment) = InsertResult(
        succeeded = comments.insertOne(comment).wasAcknowledged(),
        obj = comment
    )

    override suspend fun findById(id: String) = FindResult(
        obj = comments.findOneById(id)
    )

    override suspend fun delete(
        id: String,
        authorId: String
    ) = DeleteResult<Comment>(
        deleteCount = comments.deleteOne(
            and(
                Comment::id eq id,
                Comment::authorId eq authorId
            )
        ).deletedCount
    )

    override suspend fun delete(postId: String) {
        comments.deleteMany(Comment::postId eq postId)
    }

    override suspend fun getAll(postId: String) = FindManyResult(
        items = comments.find(Comment::postId eq postId).toList()
    )
}