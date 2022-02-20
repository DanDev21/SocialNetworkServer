package com.example.data.repository.activity

import com.example.data.dto.util.CrudResult.*
import com.example.data.entity.Activity
import com.example.core.util.Length

interface ActivityRepository {

    suspend fun add(activity: Activity): InsertResult<Activity>

    suspend fun delete(id: String, authorUsername: String): DeleteResult<Activity>

    suspend fun getAll(
        pageNumber: Int = 0,
        pageSize: Int = Length.ACTIVITY_PAGE,
        targetedUserId: String,
    ): FindManyResult<Activity>
}