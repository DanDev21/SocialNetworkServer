package com.example.domain.repository

import com.example.data.dto.util.CrudResult.*
import com.example.domain.entity.Follow
import com.example.util.AppException.RepositoryException
import com.example.util.AppException.RepositoryException.Repo
import com.example.data.repository.FollowRepository
import org.litote.kmongo.and
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq
import org.litote.kmongo.or

class FollowRepository(
    database: CoroutineDatabase
) : FollowRepository {

    private val follows = database.getCollection<Follow>()

    override suspend fun add(follow: Follow): InsertResult<Follow> {
        if (exists(follow)) {
            throw RepositoryException(Repo.ALREADY_FOLLOW)
        }
        return InsertResult(
            data = if (follows.insertOne(follow).wasAcknowledged()) follow else null
        )
    }

    private suspend fun exists(follow: Follow) =
        findByIds(follow.followerId, follow.followedUserId).succeeded

    override suspend fun findById(id: String) = FindResult(
        data = follows.findOneById(id)
    )

    override suspend fun findByIds(
        userId: String,
        otherUserId: String
    ) = FindResult(
        data = follows.findOne(
            or(
                and(
                    Follow::followerId eq userId,
                    Follow::followedUserId eq otherUserId
                ),
                and(
                    Follow::followedUserId eq otherUserId,
                    Follow::followerId eq userId
                )
            )
        )
    )

    override suspend fun findByOrderedIds(
        followerId: String,
        followedUserId: String
    ) = FindResult(
        data = follows.findOne(
            and(
                Follow::followerId eq followerId,
                Follow::followedUserId eq followedUserId
            )
        )
    )

    override suspend fun findByFollowerId(id: String) = FindResult(
        data = follows.find(Follow::followerId eq id).toList()
    )

    override suspend fun findByFollowedUserId(id: String) = FindResult(
        data = follows.find(Follow::followedUserId eq id).toList()
    )

    override suspend fun delete(
        followerId: String,
        followedUserId: String
    ) = DeleteResult(
        data = follows.findOneAndDelete(
            and(
                Follow::followerId eq followerId,
                Follow::followedUserId eq followedUserId
            )
        )
    )
}