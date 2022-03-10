package com.example.domain.repository

import com.example.data.dto.util.CrudResult.*
import com.example.domain.entity.Like
import com.example.util.AppException.RepositoryException
import com.example.util.AppException.RepositoryException.Repo
import com.example.data.repository.LikeRepository
import org.litote.kmongo.and
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq

class LikeRepository(
    database: CoroutineDatabase
) : LikeRepository {

    private val likes = database.getCollection<Like>()

    override suspend fun add(like: Like): InsertResult<Like> {
        if (exists(like)) {
            throw RepositoryException(Repo.ALREADY_LIKED)
        }
        return InsertResult(
            data = if (likes.insertOne(like).wasAcknowledged()) like else null
        )
    }

    private suspend fun exists(like: Like) =
        likes.findOne(
            and(
                Like::authorId eq like.authorId,
                Like::targetId eq like.targetId
            )
        ) != null

    override suspend fun findById(id: String) = FindResult(
        data = likes.findOneById(id)
    )

    override suspend fun delete(
        id: String,
        authorId: String
    ) = DeleteResult(
        data = likes.findOneAndDelete(
            and(
                Like::id eq id,
                Like::authorId eq authorId
            )
        )
    )

    override suspend fun delete(targetId: String) {
        likes.deleteMany(Like::targetId eq targetId)
    }

    override suspend fun getAll(authorId: String) = FindResult(
        data = likes.find(Like::authorId eq authorId).toList()
    )
}