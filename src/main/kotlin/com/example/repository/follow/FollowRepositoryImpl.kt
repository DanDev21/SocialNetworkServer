package com.example.repository.follow

import com.example.domain.model.Follow
import com.example.domain.util.AppException
import com.example.domain.util.Constants
import org.litote.kmongo.and
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq

class FollowRepositoryImpl(
    database: CoroutineDatabase
) : FollowRepository {

    private val follows = database.getCollection<Follow>()

    override suspend fun add(follow: Follow) {
        if (findByIds(follow.byWhoId, follow.otherId) != null) {
            throw AppException.Repo.AlreadyFollow
        }
        follows.insertOne(follow)
    }

    override suspend fun findByIds(byWhoId: String, otherId: String) =
        follows.findOne(
            and(
                Follow::byWhoId eq byWhoId,
                Follow::otherId eq otherId
            )
        )

    override suspend fun findByByWhoId(id: String) =
        follows.find(Follow::byWhoId eq id).toList()

    override suspend fun findByOtherId(id: String) =
        follows.find(Follow::otherId eq id).toList()

    override suspend fun delete(byWhoId: String, otherId: String) =
        follows.deleteOne(
            and(
                Follow::byWhoId eq byWhoId,
                Follow::otherId eq otherId
            )
        ).deletedCount != 0L
}