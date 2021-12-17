package com.example.repository.user

import com.example.domain.model.Credential
import com.example.domain.model.User
import com.example.domain.util.AppException
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq

class UserRepositoryImpl(
    database: CoroutineDatabase
) : UserRepository {

    private val users = database.getCollection<User>()

    override suspend fun add(user: User) {
        if (findByEmail(user.email) != null) {
            throw AppException.Repo.EmailTaken
        }
        if (findByUsername(user.username) != null) {
            throw AppException.Repo.UsernameTaken
        }
        users.insertOne(user)
    }

    override suspend fun findById(id: String) =
        users.findOneById(id)

    override suspend fun findByEmail(email: String) =
        users.findOne(User::email eq email)

    override suspend fun findByUsername(username: String) =
        users.findOne(User::username eq username)

    override suspend fun findByCredentials(credential: Credential): User {
        val user = users.findOne(User::password eq credential.password)
        if (user != null) {
            if (user.email == credential.emailOrUsername ||
                user.username == credential.emailOrUsername) {
                return user
            } else throw AppException.Repo.CredentialsDoNotMatch
        } else throw AppException.Repo.CredentialsDoNotMatch
    }
}