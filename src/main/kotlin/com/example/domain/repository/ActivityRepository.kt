package com.example.domain.repository

import com.example.data.dto.util.CrudResult.*
import com.example.domain.entity.Activity
import com.example.data.repository.ActivityRepository
import org.litote.kmongo.and
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq

class ActivityRepository(
    database: CoroutineDatabase
): ActivityRepository {

    private val activities = database.getCollection<Activity>()

    override suspend fun add(activity: Activity) = InsertResult(
        data = if (activities.insertOne(activity).wasAcknowledged()) activity else null
    )

    override suspend fun delete(
        id: String,
        authorUsername: String
    ) = DeleteResult(
        data = activities.findOneAndDelete(
            and(
                Activity::id eq id,
                Activity::authorId eq authorUsername
            )
        )
    )

    override suspend fun getAll(
        pageNumber: Int,
        pageSize: Int,
        targetedUserId: String,
    ) = FindResult(
        data = activities.find(Activity::targetedUserId eq targetedUserId)
            .skip(pageNumber * pageSize)
            .limit(pageSize)
            .descendingSort(Activity::timestamp)
            .toList()
    )
}