package com.example.data.repository

import com.example.data.dto.util.CrudResult.*
import com.example.domain.entity.Activity
import com.example.Length

interface ActivityRepository {

    suspend fun add(activity: Activity): InsertResult<Activity>

    suspend fun delete(id: String, authorUsername: String): DeleteResult<Activity>

    suspend fun getAll(
        pageNumber: Int = 0,
        pageSize: Int = Length.PAGINATED_RESOURCE_PAGE,
        targetedUserId: String,
    ): FindResult<List<Activity>>
}