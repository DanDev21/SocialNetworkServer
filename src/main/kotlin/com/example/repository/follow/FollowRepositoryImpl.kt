package com.example.repository.follow

import com.example.domain.data.dto.crud.CrudResult.*
import com.example.domain.model.Follow
import com.example.core.AppException.RepositoryException
import com.example.domain.util.Repo
import org.litote.kmongo.and
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq
import org.litote.kmongo.or

class FollowRepositoryImpl(
    database: CoroutineDatabase
) : FollowRepository {

    private val follows = database.getCollection<Follow>()

    override suspend fun add(follow: Follow): InsertResult<Follow> {
        if (exists(follow)) {
            throw RepositoryException(Repo.ALREADY_FOLLOW)
        }
        return InsertResult(
            succeeded = follows.insertOne(follow).wasAcknowledged(),
            obj = follow
        )
    }

    private suspend fun exists(follow: Follow) =
        findByIds(follow.followerId, follow.followedUserId).founded

    override suspend fun findById(id: String) = FindResult(
        obj = follows.findOneById(id)
    )

    override suspend fun findByIds(
        userId: String,
        otherUserId: String
    ) = FindResult(
        obj = follows.findOne(
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
        obj = follows.findOne(
            and(
                Follow::followerId eq followerId,
                Follow::followedUserId eq followedUserId
            )
        )
    )

    override suspend fun findByFollowerId(id: String) = FindManyResult(
        items = follows.find(Follow::followerId eq id).toList()
    )

    override suspend fun findByFollowedUserId(id: String) = FindManyResult(
        items = follows.find(Follow::followedUserId eq id).toList()
    )

    override suspend fun delete(
        followerId: String,
        followedUserId: String
    ) = DeleteResult<Follow>(
        deleteCount = follows.deleteOne(
            and(
                Follow::followerId eq followerId,
                Follow::followedUserId eq followedUserId
            )
        ).deletedCount
    )
}