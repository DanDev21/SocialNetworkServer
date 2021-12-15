package com.example.repository.user

import com.example.domain.model.User
import com.example.domain.util.Constants
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq
import org.litote.kmongo.or

class UserRepositoryImpl(
    database: CoroutineDatabase
) : UserRepository {

    private val users = database.getCollection<User>()

    @Throws(Exception::class)
    override suspend fun add(user: User) {
        if (this.getByEmailOrUsername(user.email, user.username) != null) {
            throw Exception(Constants.Error.Repository.USER_ALREADY_EXISTS)
        }
        users.insertOne(user)
    }

    override suspend fun getById(id: String) =
        users.findOneById(id)

    override suspend fun getByEmailOrUsername(email: String, username: String) =
        users.findOne(
            or(
                User::email eq email,
                User::username eq username
            )
        )
}