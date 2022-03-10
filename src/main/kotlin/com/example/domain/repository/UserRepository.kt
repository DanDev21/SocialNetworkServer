package com.example.domain.repository

import com.example.util.AppException.RepositoryException
import com.example.data.dto.request.user.UpdateUserRequest
import com.example.data.dto.util.CrudResult.*
import com.example.domain.entity.User
import com.example.data.repository.UserRepository
import com.example.util.AppException.RepositoryException.Repo
import org.litote.kmongo.*
import org.litote.kmongo.coroutine.CoroutineDatabase

class UserRepository(
    database: CoroutineDatabase
) : UserRepository {

    private val users = database.getCollection<User>()

    override suspend fun add(user: User): InsertResult<User> {
        if (exists(user)) {
            throw RepositoryException(Repo.EMAIL_OR_USERNAME_TAKEN)
        }
        return InsertResult(
            data = if (users.insertOne(user).wasAcknowledged()) user else null
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
        data = users.findOneById(id)
    )

    override suspend fun update(
        userId: String,
        request: UpdateUserRequest,
    ) = UpdateResult(
        data = users.findOneAndUpdate(
            filter = User::id eq userId,
            update = set(
                User::username setTo request.username,
                User::bio setTo request.bio,
                User::github setTo request.github,
                User::linkedIn setTo request.linkedIn,
                User::instagram setTo request.instagram,
                User::skills setTo request.skills,
            )
        )
    )

    override suspend fun update(
        userId: String,
        profileImageUrl: String
    ) = UpdateResult(
        data = users.findOneAndUpdate(
            filter = User::id eq userId,
            update = set(User::profileImageUrl setTo profileImageUrl),
        )
    )

    override suspend fun findByUsername(
        regex: String,
        requesterId: String
    ) = FindResult(
        data = users.find(
            and(
                User::id ne requesterId,
                User::username regex Regex("(?i).*$regex.*"),
            )
        )
            .descendingSort()
            .toList()
    )

    override suspend fun findByEmailOrUsername(emailOrUsername: String) =
        FindResult(
            data = users.findOne(
                or(
                    User::email eq emailOrUsername,
                    User::username eq emailOrUsername
                )
            )
        )
}