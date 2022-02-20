package com.example.data.repository.user

import com.example.data.dto.util.CrudResult.*
import com.example.data.entity.User
import com.example.core.AppException.RepositoryException
import com.example.core.Repo
import com.example.data.dto.request.user.UpdateProfileRequest
import org.litote.kmongo.*
import org.litote.kmongo.coroutine.CoroutineDatabase

class UserRepositoryImpl(
    database: CoroutineDatabase
) : UserRepository {

    private val users = database.getCollection<User>()

    override suspend fun add(user: User): InsertResult<User> {
        if (exists(user)) {
            throw RepositoryException(Repo.EMAIL_OR_USERNAME_TAKEN)
        }
        return InsertResult(
            succeeded = users.insertOne(user).wasAcknowledged(),
            obj = user
        )
    }

    private suspend fun exists(user: User) =
        users.findOne(
            or(
                User::email eq  user.email,
                User::username eq user.username
            )
        ) != null

    override suspend fun findById(id: String) = FindResult(
        obj = users.findOneById(id)
    )

    override suspend fun update(
        userId: String,
        request: UpdateProfileRequest,
    ) = UpdateResult<User>(
        succeeded = users.updateOne(
            User::id eq userId,
            set(
                User::username setTo request.username,
                User::bio setTo request.bio,
                User::github setTo request.github,
                User::linkedIn setTo request.linkedIn,
                User::instagram setTo request.instagram,
                User::skills setTo request.skills,
            )
        ).wasAcknowledged()
    )

    override suspend fun update(
        userId: String,
        profileImageUrl: String
    ) = UpdateResult<User>(
        succeeded = users.updateOneById(
            id = userId,
            update = User::profileImageUrl setTo profileImageUrl
        ).wasAcknowledged()
    )

    override suspend fun findByUsername(
        regex: String,
        requesterId: String
    ) = FindManyResult(
        items = users.find(
            and(
                User::id ne requesterId,
                User::username regex Regex("(?i).*$regex.*"),
            )
        )
            .descendingSort()
            .toList()
    )

    override suspend fun findByEmailOrUsername(emailOrUsername: String) = FindResult(
        obj = users.findOne(
            or(
                User::email eq emailOrUsername,
                User::username eq emailOrUsername
            )
        )
    )
}