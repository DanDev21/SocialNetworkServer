package com.example.use_case.user

import com.example.repository.user.UserRepository

class FindUsers(
    private val repository: UserRepository
) {

    suspend operator fun invoke(id: String) =
        repository.findById(id)

    suspend fun with(regex: String) =
        repository.findByUsername(regex)
}