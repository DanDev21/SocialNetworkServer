package com.example.use_case.user

import com.example.repository.user.UserRepository

class FindUsers(
    private val userRepository: UserRepository,
) {

    suspend operator fun invoke(searchableId: String) =
        userRepository.findById(searchableId)

    suspend operator fun invoke(
        regex: String,
        userId: String
    ) = userRepository.findByUsername(
        regex = regex,
        id = userId
    )
}