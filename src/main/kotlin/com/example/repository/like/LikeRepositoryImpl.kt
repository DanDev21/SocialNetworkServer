package com.example.repository.like

import com.example.domain.model.Like
import org.litote.kmongo.and
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq

class LikeRepositoryImpl(
    database: CoroutineDatabase
) : LikeRepository {

    private val likes = database.getCollection<Like>()

    override suspend fun add(like: Like): Boolean {
        if (exists(like)) {
            throw Exception("the target is already liked by the user!")
        }
        return likes.insertOne(like).wasAcknowledged()
    }

    private suspend fun exists(like: Like): Boolean =
        likes.findOne(
            and(
                Like::userId eq like.userId,
                Like::targetId eq like.targetId
            )
        ) != null

    override suspend fun delete(id: String, authorId: String): Boolean =
        likes.deleteOne(
            and(
                Like::id eq id,
                Like::userId eq authorId
            )
        ).deletedCount > 0

    override suspend fun deleteAll(targetId: String) {
        likes.deleteMany(Like::targetId eq targetId)
    }

    override suspend fun getAll(userId: String): List<Like> =
        likes.find(Like::userId eq userId).toList()
}