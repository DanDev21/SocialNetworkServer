package com.example.repository.user

import com.example.domain.model.User
import com.example.domain.util.AppException.Repository
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq
import org.litote.kmongo.or

class UserRepositoryImpl(
    database: CoroutineDatabase
) : UserRepository {

    private val users = database.getCollection<User>()

    override suspend fun add(user: User) {
        if (findByCredentials(user.email, user.username) != null) {
            throw Repository.EmailOrUsernameTaken
        }
        users.insertOne(user)
    }

    override suspend fun findById(id: String) =
        users.findOneById(id)

    override suspend fun findByCredentials(
        email: String,
        username: String
    ) = users.findOne(
        or(
            User::email eq email,
            User::username eq username
        )
    )

    override suspend fun findByUsername(username: String) =
        users.findOne(User::username eq username)
}