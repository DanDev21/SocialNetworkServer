package com.example.repository.user

import com.example.domain.model.Credential
import com.example.domain.model.User
import com.example.domain.util.AppException

internal class FakeUserRepository : UserRepository {

    private val users = mutableListOf<User>()

    override suspend fun add(user: User) {
        if (findByEmail(user.email) != null) {
            throw AppException.Repo.EmailTaken
        }
        if (findByUsername(user.username) != null) {
            throw AppException.Repo.UsernameTaken
        }
        users.add(user)
    }

    override suspend fun findById(id: String) =
        users.find { it.id == id }

    override suspend fun findByEmail(email: String) =
        users.find { it.email == email }

    override suspend fun findByUsername(username: String) =
        users.find { it.username == username }

    override suspend fun findByCredentials(credential: Credential): User {
        val user = users.find { it.password == credential.password }
        if (user != null) {
            if (user.email == credential.emailOrUsername ||
                user.username == credential.emailOrUsername) {
                return user
            } else throw AppException.Repo.CredentialsDoNotMatch
        } else throw AppException.Repo.CredentialsDoNotMatch
    }
}