package com.example.repository.user

import com.example.domain.model.User
import com.example.domain.util.AppException
import com.example.domain.util.Constants
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq

class UserRepositoryImpl(
    database: CoroutineDatabase
) : UserRepository {

    private val users = database.getCollection<User>()

    override suspend fun add(user: User) {
        if (findByEmail(user.email) != null) {
            throw AppException.RepositoryException(Constants.Error.Repository.EMAIL_TAKEN)
        }
        if (findByUsername(user.username) != null) {
            throw AppException.RepositoryException(Constants.Error.Repository.USERNAME_TAKEN)
        }
        users.insertOne(user)
    }

    override suspend fun findById(id: String) =
        users.findOneById(id)

    override suspend fun findByEmail(email: String) =
        users.findOne(User::email eq email)

    override suspend fun findByUsername(username: String) =
        users.findOne(User::username eq username)

    override suspend fun findByCredentials(
        emailOrUsername: String,
        password: String
    ): User {
        val user = users.findOne(User::password eq password)
        return if (user != null) {
            if (user.email == emailOrUsername || user.username == emailOrUsername) {
                user
            } else throw AppException.RepositoryException(
                Constants.Error.Repository.CREDENTIALS_DO_NOT_MATCH
            )
        } else throw AppException.RepositoryException(
            Constants.Error.Repository.CREDENTIALS_DO_NOT_MATCH
        )
    }
}