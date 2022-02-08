package com.example.repository.like


import com.example.domain.data.dto.crud.CrudResult.*
import com.example.domain.model.Like
import com.example.core.AppException.RepositoryException
import com.example.domain.util.Repo
import org.litote.kmongo.and
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq

class LikeRepositoryImpl(
    database: CoroutineDatabase
) : LikeRepository {

    private val likes = database.getCollection<Like>()

    override suspend fun add(like: Like): InsertResult<Like> {
        if (exists(like)) {
            throw RepositoryException(Repo.ALREADY_LIKED)
        }
        return InsertResult(
            succeeded = likes.insertOne(like).wasAcknowledged(),
            obj = like
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
        obj = likes.findOneById(id)
    )

    override suspend fun delete(
        id: String,
        authorId: String
    ) = DeleteResult<Like>(
        deleteCount = likes.deleteOne(
            and(
                Like::id eq id,
                Like::authorId eq authorId
            )
        ).deletedCount
    )

    override suspend fun delete(targetId: String) {
        likes.deleteMany(Like::targetId eq targetId)
    }

    override suspend fun getAll(authorId: String) = FindManyResult(
        items = likes.find(Like::authorId eq authorId).toList()
    )
}