package com.example.repository.user

import com.example.domain.model.User
import com.example.domain.util.AppException
import com.example.domain.util.Constants

internal class FakeUserRepository : UserRepository {

    private val users = mutableListOf<User>()

    override suspend fun add(user: User) {
        if (findByEmail(user.email) != null) {
            throw AppException.RepositoryException(Constants.Error.Repository.EMAIL_TAKEN)
        }
        if (findByUsername(user.username) != null) {
            throw AppException.RepositoryException(Constants.Error.Repository.USERNAME_TAKEN)
        }
        users.add(user)
    }

    override suspend fun findById(id: String) =
        users.find { it.id == id }

    override suspend fun findByEmail(email: String) =
        users.find { it.email == email }

    override suspend fun findByUsername(username: String) =
        users.find { it.username == username }

    override suspend fun findByCredentials(emailOrUsername: String, password: String) =
        users.find {
            it.password == password &&
                    (it.email == emailOrUsername || it.username == emailOrUsername)
        }
}