package com.example.repository.activity

import com.example.domain.data.dto.crud.CrudResult.*
import com.example.domain.model.Activity
import com.example.domain.util.Length

interface ActivityRepository {

    suspend fun add(activity: Activity): InsertResult<Activity>

    suspend fun delete(id: String, authorUsername: String): DeleteResult<Activity>

    suspend fun getAll(
        pageNumber: Int = 0,
        pageSize: Int = Length.ACTIVITY_PAGE,
        targetedUserId: String,
    ): FindManyResult<Activity>
}