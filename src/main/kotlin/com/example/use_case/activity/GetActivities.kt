package com.example.use_case.activity

import com.example.repository.activity.ActivityRepository

class GetActivities(
    private val repository: ActivityRepository
) {

    suspend operator fun invoke(
        pageNumber: Int,
        pageSize: Int,
        userId: String
    ) = repository.getAll(
        pageNumber = pageNumber,
        pageSize = pageSize,
        userId = userId
    )
}