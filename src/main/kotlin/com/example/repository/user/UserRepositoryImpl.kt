package com.example.repository.user

import com.example.domain.data.dto.crud.CrudResult.*
import com.example.domain.model.User
import com.example.domain.util.AppException.RepositoryException
import com.example.domain.util.Repo
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

    override suspend fun findByUsername(regex: String, id: String) = FindManyResult(
        items = users.find(
            and(
                User::id ne id,
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